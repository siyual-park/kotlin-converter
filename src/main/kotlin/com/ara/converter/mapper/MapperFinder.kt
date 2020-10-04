package com.ara.converter.mapper

import com.ara.converter.matcher.Matcher

interface MapperFinder<MAPPER_META : Any, REQUEST_MATE : Any> {
    fun register(matcher: Matcher<MAPPER_META, REQUEST_MATE>): MapperFinder<MAPPER_META, REQUEST_MATE>

    fun <SOURCE : Any, TARGET : Any> register(
        mapper: Mapper<SOURCE, TARGET>,
        info: MapperInfo<SOURCE, TARGET>,
        meta: MAPPER_META? = null
    ): MapperFinder<MAPPER_META, REQUEST_MATE>

    fun <SOURCE : Any, TARGET : Any> find(
        info: MapperInfo<SOURCE, TARGET>,
        meta: REQUEST_MATE? = null
    ): Mapper<SOURCE, TARGET>
}