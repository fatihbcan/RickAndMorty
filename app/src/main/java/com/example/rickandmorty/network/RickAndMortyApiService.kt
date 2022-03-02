package com.example.rickandmorty.network

import com.example.rickandmorty.data.detailPageData.Episode
import retrofit2.http.*

interface RickAndMortyApiService {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): RickAndMortyApiListItemsResponse

    @GET("character")
    suspend fun getCharactersByStatus(
        @Query("page") page: Int,
        @Query("status") status: String
    ): RickAndMortyApiListItemsResponse

    @GET("episode/{episodeList}")
    suspend fun getIncludedEpisodesOfCharacter(
        @Path("episodeList") epsiodeList: String
    ): List<Episode>
}