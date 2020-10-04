package com.ara.converter.mock

import com.ara.converter.mapper.Mapper

object IntMinusMapper : Mapper<Int, Int> {
    override fun map(source: Int): Int {
        return source - 1
    }
}