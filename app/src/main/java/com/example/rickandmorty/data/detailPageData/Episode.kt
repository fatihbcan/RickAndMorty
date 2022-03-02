package com.example.rickandmorty.data.detailPageData

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episode(
    @SerializedName("name") val episodeName: String,
    @SerializedName("air_date") val episodeAirDate: String,
    @SerializedName("episode") val episodeSE: String,
    ) : Parcelable
