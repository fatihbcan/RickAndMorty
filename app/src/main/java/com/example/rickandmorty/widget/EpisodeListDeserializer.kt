package com.example.rickandmorty.widget

import com.example.rickandmorty.data.detailPageData.Episode
import com.example.rickandmorty.data.detailPageData.EpisodeList
import com.google.gson.*
import java.lang.reflect.Type

class EpisodeListDeserializer : JsonDeserializer<EpisodeList?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EpisodeList {
        val value = EpisodeList()
        val gson = Gson()
        if (json.isJsonArray) {
            for (element in json.asJsonArray) {
                value.add(gson.fromJson(element, Episode::class.java))
            }
        } else {
            val element: JsonElement = json.asJsonObject
            value.add(gson.fromJson(element, Episode::class.java))
        }
        return value
    }
}