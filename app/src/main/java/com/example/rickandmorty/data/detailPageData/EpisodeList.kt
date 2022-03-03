package com.example.rickandmorty.data.detailPageData

import java.io.Serializable

class EpisodeList : Serializable{
    var episodeList: ArrayList<Episode> = ArrayList()

    fun add(episode: Episode) {
        episodeList.add(episode)
    }
}