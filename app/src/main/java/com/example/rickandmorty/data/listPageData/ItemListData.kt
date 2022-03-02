package com.example.rickandmorty.data.listPageData

import android.os.Parcelable
import com.example.rickandmorty.data.detailPageData.Location
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemListData(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("episode") val episodeList: List<String>,
    @SerializedName("location") val location: Location
) : Parcelable
