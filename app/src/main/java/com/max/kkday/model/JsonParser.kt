package com.max.kkday.model

import java.lang.reflect.Type

interface JsonParser {
    fun <T : Any> fromJson(jsonString: String, type: Type): T
    fun toJson(jsonObject: Any): String
}

inline fun <reified T : Any> JsonParser.fromJson(jsonString: String): T {
    return fromJson(jsonString, T::class.java)
}