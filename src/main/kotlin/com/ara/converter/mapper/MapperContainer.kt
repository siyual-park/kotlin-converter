package com.ara.converter.mapper

data class MapperContainer<SOURCE: Any, TARGET: Any, META: Any>(
    val info: MapperInfo<SOURCE, TARGET>,
    val mapper: Mapper<SOURCE, TARGET>,
    val meta: META?
)