package com.ara.converter.mapper

import com.ara.converter.matcher.Matcher
import java.lang.RuntimeException

@Suppress("UNCHECKED_CAST")
class MapperFinder<META: Any> {
    private val matchers: MutableList<Matcher<META>> = mutableListOf()
    private val mappers: MutableList<MapperContainer<*, *, META>> = mutableListOf()
    private val cache: MutableMap<MapperInfo<*, *>, Mapper<*, *>> = mutableMapOf()

    fun register(matcher: Matcher<META>): MapperFinder<META> = this.apply {
        matchers.add(matcher)
        cache.clear()
    }

    fun <SOURCE: Any, TARGET: Any> register(mapper: Mapper<SOURCE, TARGET>, info: MapperInfo<SOURCE, TARGET>, meta: META? = null): MapperFinder<META> = this.apply {
        mappers.add(MapperContainer(info, mapper, meta))
        cache.clear()
    }

    fun <SOURCE: Any, TARGET: Any> find(info: MapperInfo<SOURCE, TARGET>, meta: META? = null): Mapper<SOURCE, TARGET> {
        return cache.getOrPut(info) {
            for (matcher in matchers) {
                for ((key, mapper, meta) in mappers) {
                    if (matcher.match(key, info, meta)) {
                        return@getOrPut mapper
                    }
                }
            }

            throw RuntimeException("Cant find mapper for $info")
        } as Mapper<SOURCE, TARGET>
    }
}