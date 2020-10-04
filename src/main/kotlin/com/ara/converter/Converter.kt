package com.ara.converter

import com.ara.converter.mapper.*
import com.ara.converter.matcher.Matcher
import java.util.*

class Converter<MAPPER_META : Any, REQUEST_MATE : Any>(
    private val mapperFinder: MapperFinder<MAPPER_META, REQUEST_MATE>
) {
    fun register(matcher: Matcher<MAPPER_META, REQUEST_MATE>): Converter<MAPPER_META, REQUEST_MATE> = this.apply {
        mapperFinder.register(matcher)
    }

    fun <SOURCE : Any, TARGET : Any> register(
        mapper: Mapper<SOURCE, TARGET>,
        info: MapperInfo<SOURCE, TARGET>,
        meta: MAPPER_META? = null
    ): Converter<MAPPER_META, REQUEST_MATE> = this.apply {
        mapperFinder.register(mapper, info, meta)
    }

    fun <SOURCE : Any, TARGET : Any> convert(
        source: SOURCE,
        info: MapperInfo<SOURCE, TARGET>,
        meta: REQUEST_MATE? = null
    ): TARGET {
        return mapperFinder.find(info, meta).map(source)
    }

    companion object {
        fun <MAPPER_META : Any, REQUEST_MATE : Any> create(
            vararg matchers: Matcher<MAPPER_META, REQUEST_MATE>,
            mapperFinder: MapperFinder<MAPPER_META, REQUEST_MATE> = DefaultMapperFinder(),
            cache: MutableMap<Pair<MapperInfo<*, *>, REQUEST_MATE?>, Mapper<*, *>>? = WeakHashMap()
        ) = Converter(
            if (cache != null) {
                CachedMapperFinder(mapperFinder, cache)
            } else {
                mapperFinder
            }
        ).apply {
            matchers.forEach { register(it) }
        }
    }
}

inline fun <reified SOURCE: Any, reified TARGET: Any, MAPPER_META: Any, REQUEST_MATE: Any> Converter<MAPPER_META, REQUEST_MATE>.register(mapper: Mapper<SOURCE, TARGET>, meta: MAPPER_META? = null): Converter<MAPPER_META, REQUEST_MATE> {
    return this.register(mapper, MapperInfo.create(), meta)
}

inline fun <reified SOURCE: Any, reified TARGET: Any, MAPPER_META: Any, REQUEST_MATE: Any> Converter<MAPPER_META, REQUEST_MATE>.convert(source: SOURCE, meta: REQUEST_MATE? = null): TARGET {
    return this.convert(source, MapperInfo.create(), meta)
}