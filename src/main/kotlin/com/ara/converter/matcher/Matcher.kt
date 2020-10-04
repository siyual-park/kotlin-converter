package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo

interface Matcher<MAPPER_META: Any, REQUEST_MATE: Any> {
    fun match(origin: MapperInfo<*, *>, request: MapperInfo<*, *>, mapperMeta: MAPPER_META? = null, requestMeta: REQUEST_MATE? = null): Boolean
}