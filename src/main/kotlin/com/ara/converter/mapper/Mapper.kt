package com.ara.converter.mapper

interface Mapper<SOURCE: Any, TARGET: Any> {
    fun map(source: SOURCE): TARGET
}