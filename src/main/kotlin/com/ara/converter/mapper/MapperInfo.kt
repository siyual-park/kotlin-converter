package com.ara.converter.mapper

import kotlin.reflect.KClass

data class MapperInfo<SOURCE: Any, TARGET: Any>(
    val source: KClass<SOURCE>,
    val target: KClass<TARGET>
) {
    companion object {
        inline fun <reified SOURCE: Any, reified TARGET: Any> create(): MapperInfo<SOURCE, TARGET> {
            return MapperInfo(SOURCE::class, TARGET::class)
        }
    }
}