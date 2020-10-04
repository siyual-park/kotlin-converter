package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

class NameMatcher(
    private val matcher: Matcher<String, String>
) : Matcher<String, String> {

    override fun match(origin: MapperInfo<*, *>, request: MapperInfo<*, *>, mapperMeta: String?, requestMeta: String?): Boolean {
        return matcher.match(origin, request, mapperMeta, requestMeta) && mapperMeta == requestMeta
    }

    companion object {
        fun from(matcher: Matcher<String, String>): NameMatcher {
            return NameMatcher(matcher)
        }
    }
}