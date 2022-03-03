package com.example.rickandmorty.widget

import com.example.rickandmorty.data.detailPageData.Episode
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class EpisodesDeserializer : JsonDeserializer<Episode?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type,
        context: JsonDeserializationContext
    ): Episode? {
        return null
    }
}

