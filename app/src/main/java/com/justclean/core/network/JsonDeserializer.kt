package com.justclean.core.network

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.justclean.networking.Model
import java.lang.reflect.Type

object JsonDeserializer {

    fun getLanguages(json: JsonElement): Model<List<Language>> {
        val type: Type = object : TypeToken<Model<List<Language>>>() {}.type
        return Gson().fromJson<Model<List<Language>>>(json, type)
    }
}