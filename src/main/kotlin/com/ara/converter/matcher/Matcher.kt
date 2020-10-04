package com.ara.converter.matcher

import com.ara.converter.mapper.MapperInfo

interface Matcher<META: Any> {
    fun match(origin: MapperInfo<*, *>, request: MapperInfo<*, *>, meta: META? = null): Boolean
}