package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo

class AndMatcher<MAPPER_META: Any, REQUEST_MATE: Any>(
    private val matchers: Collection<Matcher<MAPPER_META, REQUEST_MATE>>
) : Matcher<MAPPER_META, REQUEST_MATE> {
    override fun match(
        origin: MapperInfo<*, *>,
        request: MapperInfo<*, *>,
        mapperMeta: MAPPER_META?,
        requestMeta: REQUEST_MATE?
    ): Boolean {
        return matchers.all { it.match(origin, request, mapperMeta, requestMeta) }
    }

    companion object {
        fun <MAPPER_META: Any, REQUEST_MATE: Any> create(vararg matchers: Matcher<MAPPER_META, REQUEST_MATE>): AndMatcher<MAPPER_META, REQUEST_MATE> {
            return AndMatcher(matchers.toList())
        }
    }
}

fun <MAPPER_META: Any, REQUEST_MATE: Any> Matcher.Companion.and(vararg matchers: Matcher<MAPPER_META, REQUEST_MATE>) = AndMatcher.create(*matchers)