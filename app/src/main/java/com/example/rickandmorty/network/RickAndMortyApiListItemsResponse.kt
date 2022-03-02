package com.example.rickandmorty.network

import com.example.rickandmorty.data.listPageData.ItemListData
import com.google.gson.annotations.SerializedName

data class RickAndMortyApiListItemsResponse (
    @SerializedName("results") val results : List<ItemListData>
)