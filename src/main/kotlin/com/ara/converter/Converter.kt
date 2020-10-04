package com.ara.converter

import com.ara.converter.mapper.Mapper
import com.ara.converter.mapper.MapperFinder
import com.ara.converter.mapper.MapperInfo
import com.ara.converter.matcher.Matcher

class Converter<META: Any> {
    private val mapperFinder: MapperFinder<META> = MapperFinder()

    fun register(matcher: Matcher<META>): Converter<META> = this.apply {
        mapperFinder.register(matcher)
    }


    fun <SOURCE: Any, TARGET: Any> register(mapper: Mapper<SOURCE, TARGET>, info: MapperInfo<SOURCE, TARGET>, meta: META? = null): Converter<META> = this.apply {
        mapperFinder.register(mapper, info, meta)
    }

    fun <SOURCE: Any, TARGET: Any> convert(source: SOURCE, info: MapperInfo<SOURCE, TARGET>, meta: META? = null): TARGET {
        return mapperFinder.find(info, meta).map(source)
    }

    companion object {
        fun <META: Any> create(vararg matchers: Matcher<META>) = Converter<META>().apply {
            matchers.forEach { register(it) }
        }
    }
}

inline fun <reified SOURCE: Any, reified TARGET: Any, META: Any> Converter<META>.register(mapper: Mapper<SOURCE, TARGET>, meta: META? = null): Converter<META> {
    return this.register(mapper, MapperInfo.create(), meta)
}

inline fun <reified SOURCE: Any, reified TARGET: Any, META: Any> Converter<META>.convert(source: SOURCE, meta: META? = null): TARGET {
    return this.convert(source, MapperInfo.create(), meta)
}