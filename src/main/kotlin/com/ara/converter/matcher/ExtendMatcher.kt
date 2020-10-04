package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

class ExtendMatcher<MAPPER_META: Any, REQUEST_MATE: Any> : Matcher<MAPPER_META, REQUEST_MATE> {
    override fun match(origin: MapperInfo<*, *>, request: MapperInfo<*, *>, mapperMeta: MAPPER_META?, requestMeta: REQUEST_MATE?): Boolean {
        return origin.source.isSuperclassOf(request.source) && origin.target.isSubclassOf(request.target)
    }

    companion object {
        fun <MAPPER_META: Any, REQUEST_MATE: Any> create(): ExtendMatcher<MAPPER_META, REQUEST_MATE> {
            return ExtendMatcher()
        }
    }
}