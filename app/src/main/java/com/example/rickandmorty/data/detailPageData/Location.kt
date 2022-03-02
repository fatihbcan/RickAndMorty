package com.example.rickandmorty.data.detailPageData

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @SerializedName("name") val locationName: String,
) : Parcelable
