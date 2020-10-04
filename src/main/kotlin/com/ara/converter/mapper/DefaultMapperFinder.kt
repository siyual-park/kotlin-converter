package com.ara.converter.mapper

import com.ara.converter.matcher.Matcher

@Suppress("UNCHECKED_CAST")
class DefaultMapperFinder<MAPPER_META : Any, REQUEST_MATE : Any> : MapperFinder<MAPPER_META, REQUEST_MATE> {
    private val matchers: MutableList<Matcher<MAPPER_META, REQUEST_MATE>> = mutableListOf()
    private val mappers: MutableList<MapperContainer<*, *, MAPPER_META>> = mutableListOf()

    override fun register(matcher: Matcher<MAPPER_META, REQUEST_MATE>): DefaultMapperFinder<MAPPER_META, REQUEST_MATE> =
        this.apply {
            matchers.add(matcher)
        }

    override fun <SOURCE : Any, TARGET : Any> register(
        mapper: Mapper<SOURCE, TARGET>,
        info: MapperInfo<SOURCE, TARGET>,
        meta: MAPPER_META?
    ): DefaultMapperFinder<MAPPER_META, REQUEST_MATE> = this.apply {
        mappers.add(MapperContainer(info, mapper, meta))
    }

    override fun <SOURCE : Any, TARGET : Any> find(
        info: MapperInfo<SOURCE, TARGET>,
        meta: REQUEST_MATE?
    ): Mapper<SOURCE, TARGET> {
        for (matcher in matchers) {
            for ((key, mapper, mapperMeta) in mappers) {
                if (matcher.match(key, info, mapperMeta, meta)) {
                    return mapper as Mapper<SOURCE, TARGET>
                }
            }
        }

        throw RuntimeException("Cant find mapper for $info")
    }
}