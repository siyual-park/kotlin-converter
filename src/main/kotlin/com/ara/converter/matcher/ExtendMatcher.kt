package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

class ExtendMatcher<META: Any> : Matcher<META> {
    override fun match(origin: MapperInfo<*, *>, request: MapperInfo<*, *>, meta: META?): Boolean {
        return origin.source.isSuperclassOf(request.source) && origin.target.isSubclassOf(request.target)
    }

    companion object {
        fun <META: Any> create(): ExtendMatcher<META> {
            return ExtendMatcher()
        }
    }
}