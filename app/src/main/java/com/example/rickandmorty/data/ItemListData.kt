package com.example.rickandmorty.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemListData(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("image") val imageUrl: String
) : Parcelable
