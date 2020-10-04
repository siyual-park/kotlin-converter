package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo

class ExactMatcher<MAPPER_META: Any, REQUEST_MATE: Any> : Matcher<MAPPER_META, REQUEST_MATE> {
    override fun match(origin: MapperInfo<*, *>, request: MapperInfo<*, *>, mapperMeta: MAPPER_META?, requestMeta: REQUEST_MATE?): Boolean {
        return origin == request
    }

    companion object {
        fun <MAPPER_META: Any, REQUEST_MATE: Any> create(): ExactMatcher<MAPPER_META, REQUEST_MATE> {
            return ExactMatcher()
        }
    }
}