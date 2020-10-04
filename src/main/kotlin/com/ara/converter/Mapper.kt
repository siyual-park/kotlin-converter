package com.ara.converter

interface Mapper<SOURCE: Any, TARGET: Any> {
    fun map(source: SOURCE): TARGET
}