package com.ara.converter

@Suppress("UNCHECKED_CAST")
class Converter {
    private val mappers: MutableMap<MapperInfo<*, *>, Mapper<*, *>> = mutableMapOf()

    fun <SOURCE: Any, TARGET: Any> register(mapper: Mapper<SOURCE, TARGET>, info: MapperInfo<out SOURCE, in TARGET>): Converter = this.apply {
        mappers[info] = mapper
    }

    fun <SOURCE: Any, TARGET: Any> convert(source: SOURCE, info: MapperInfo<out SOURCE, out TARGET>): TARGET {
        return findMapper(info).map(source)
    }

    private fun <SOURCE: Any, TARGET: Any> findMapper(info: MapperInfo<out SOURCE, out TARGET>): Mapper<SOURCE, TARGET> {
        return ((mappers[info] ?: throw RuntimeException("Mapper for $info is not defined")) as Mapper<SOURCE, TARGET>)
    }
}

inline fun <reified SOURCE: Any, reified TARGET: Any> Converter.register(mapper: Mapper<SOURCE, TARGET>): Converter {
    return this.register(mapper, MapperInfo.create())
}

inline fun <reified SOURCE: Any, reified TARGET: Any> Converter.convert(source: SOURCE): TARGET {
    return this.convert(source, MapperInfo.create())
}