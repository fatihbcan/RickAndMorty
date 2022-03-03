package com.example.rickandmorty.network

import com.example.rickandmorty.data.detailPageData.EpisodeList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): RickAndMortyApiListItemsResponse

    @GET("character")
    suspend fun getAllCharactersByName(
        @Query("page") page: Int,
        @Query("name") name: String
    ): RickAndMortyApiListItemsResponse

    @GET("character")
    suspend fun getCharactersByStatus(
        @Query("page") page: Int,
        @Query("status") status: String
    ): RickAndMortyApiListItemsResponse

    @GET("character")
    suspend fun getCharactersByStatusAndName(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String
    ): RickAndMortyApiListItemsResponse


    @GET("episode/{episodeList}")
    suspend fun getIncludedEpisodesOfCharacter(
        @Path("episodeList") epsiodeList: String
    ): EpisodeList
}