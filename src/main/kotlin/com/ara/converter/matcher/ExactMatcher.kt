package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo

class ExactMatcher<META: Any> : Matcher<META> {
    override fun match(origin: MapperInfo<*, *>, request: MapperInfo<*, *>, meta: META?): Boolean {
        return origin == request
    }

    companion object {
        fun <META: Any> create(): ExactMatcher<META> {
            return ExactMatcher()
        }
    }
}