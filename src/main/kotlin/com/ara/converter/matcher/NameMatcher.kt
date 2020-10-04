package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

class NameMatcher: Matcher<String, String> {
    override fun match(origin: MapperInfo<*, *>, request: MapperInfo<*, *>, mapperMeta: String?, requestMeta: String?): Boolean {
        return mapperMeta == requestMeta
    }

    companion object {
        fun create(): NameMatcher {
            return NameMatcher()
        }
    }
}