package com.ara.converter.mapper

import com.ara.converter.matcher.Matcher
import java.util.*

@Suppress("UNCHECKED_CAST")
class CachedMapperFinder<MAPPER_META : Any, REQUEST_MATE : Any>(
    private val mapperFinder: MapperFinder<MAPPER_META, REQUEST_MATE> = DefaultMapperFinder(),
    private val cache: MutableMap<Pair<MapperInfo<*, *>, REQUEST_MATE?>, Mapper<*, *>> = WeakHashMap()
) : MapperFinder<MAPPER_META, REQUEST_MATE> {
    override fun register(matcher: Matcher<MAPPER_META, REQUEST_MATE>): CachedMapperFinder<MAPPER_META, REQUEST_MATE> =
        this.apply {
            mapperFinder.register(matcher)
            cache.clear()
        }

    override fun <SOURCE : Any, TARGET : Any> register(
        mapper: Mapper<SOURCE, TARGET>,
        info: MapperInfo<SOURCE, TARGET>,
        meta: MAPPER_META?
    ): CachedMapperFinder<MAPPER_META, REQUEST_MATE> = this.apply {
        mapperFinder.register(mapper, info, meta)
        cache.clear()
    }

    override fun <SOURCE : Any, TARGET : Any> find(
        info: MapperInfo<SOURCE, TARGET>,
        meta: REQUEST_MATE?
    ): Mapper<SOURCE, TARGET> {
        return cache.getOrPut(Pair(info, meta)) { mapperFinder.find(info, meta) } as Mapper<SOURCE, TARGET>
    }
}