package com.example.rickandmorty.data

import com.google.gson.annotations.SerializedName

data class ItemListData(
    @SerializedName("name") val name : String,
    @SerializedName("status") val status : String,
    @SerializedName("species") val species : String,
    )
