package com.example.rickandmorty.network

import com.example.rickandmorty.data.ItemListData
import com.google.gson.annotations.SerializedName

data class RickAndMortyApiResponse (
    @SerializedName("results") val results : List<ItemListData>
)