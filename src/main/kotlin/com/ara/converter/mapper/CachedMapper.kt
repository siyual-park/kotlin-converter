package com.ara.converter.mapper

import java.util.*

class CachedMapper<MAPPER_META : Any, REQUEST_MATE : Any>(
    private val mapper: Mapper<MAPPER_META, REQUEST_MATE>,
    private val cache: MutableMap<MAPPER_META, REQUEST_MATE> = WeakHashMap()
) : Mapper<MAPPER_META, REQUEST_MATE> {
    override fun map(source: MAPPER_META): REQUEST_MATE {
        return cache.getOrPut(source) { mapper.map(source) }
    }
}