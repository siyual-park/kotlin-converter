package com.ara.converter.mapper

import com.ara.converter.matcher.Matcher
import java.lang.RuntimeException

@Suppress("UNCHECKED_CAST")
class MapperFinder<MAPPER_META: Any, REQUEST_MATE: Any> {
    private val matchers: MutableList<Matcher<MAPPER_META, REQUEST_MATE>> = mutableListOf()
    private val mappers: MutableList<MapperContainer<*, *, MAPPER_META>> = mutableListOf()
    private val cache: MutableMap<MapperInfo<*, *>, Mapper<*, *>> = mutableMapOf()

    fun register(matcher: Matcher<MAPPER_META, REQUEST_MATE>): MapperFinder<MAPPER_META, REQUEST_MATE> = this.apply {
        matchers.add(matcher)
        cache.clear()
    }

    fun <SOURCE: Any, TARGET: Any> register(mapper: Mapper<SOURCE, TARGET>, info: MapperInfo<SOURCE, TARGET>, meta: MAPPER_META? = null): MapperFinder<MAPPER_META, REQUEST_MATE> = this.apply {
        mappers.add(MapperContainer(info, mapper, meta))
        cache.clear()
    }

    fun <SOURCE: Any, TARGET: Any> find(info: MapperInfo<SOURCE, TARGET>, meta: REQUEST_MATE? = null): Mapper<SOURCE, TARGET> {
        return cache.getOrPut(info) {
            for (matcher in matchers) {
                for ((key, mapper, mapperMeta) in mappers) {
                    if (matcher.match(key, info, mapperMeta, meta)) {
                        return@getOrPut mapper
                    }
                }
            }

            throw RuntimeException("Cant find mapper for $info")
        } as Mapper<SOURCE, TARGET>
    }
}