package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo

class OrMatcher<MAPPER_META: Any, REQUEST_MATE: Any>(
    private val matchers: Collection<Matcher<MAPPER_META, REQUEST_MATE>>
) : Matcher<MAPPER_META, REQUEST_MATE> {
    override fun match(
        origin: MapperInfo<*, *>,
        request: MapperInfo<*, *>,
        mapperMeta: MAPPER_META?,
        requestMeta: REQUEST_MATE?
    ): Boolean {
        return matchers.any { it.match(origin, request, mapperMeta, requestMeta) }
    }

    companion object {
        fun <MAPPER_META: Any, REQUEST_MATE: Any> create(vararg matchers: Matcher<MAPPER_META, REQUEST_MATE>): OrMatcher<MAPPER_META, REQUEST_MATE> {
            return OrMatcher(matchers.toList())
        }
    }
}

fun <MAPPER_META: Any, REQUEST_MATE: Any> Matcher.Companion.or(vararg matchers: Matcher<MAPPER_META, REQUEST_MATE>) = OrMatcher.create(*matchers)