package com.example.rickandmorty.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): RickAndMortyApiResponse

    @GET("character")
    suspend fun getCharactersByStatus(
        @Query("page") page: Int,
        @Query("status") status: String
    ): RickAndMortyApiResponse
}