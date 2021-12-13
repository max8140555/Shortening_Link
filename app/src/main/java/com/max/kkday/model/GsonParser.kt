package com.max.kkday.model

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser(
        private val gson: Gson
) : JsonParser {
    override fun <T : Any> fromJson(jsonString: String, type: Type): T {
        return gson.fromJson(jsonString, type)
    }

    override fun toJson(jsonObject: Any): String {
        return gson.toJson(jsonObject)
    }
}