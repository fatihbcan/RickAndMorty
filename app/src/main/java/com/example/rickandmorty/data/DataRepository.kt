package com.example.rickandmorty.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.rickandmorty.network.RickAndMortyApiSearchKeys
import com.example.rickandmorty.network.RickAndMortyApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository  @Inject constructor(private val rickAndMortyApiService: RickAndMortyApiService) {

    fun getSearchResults(category: String) =
        if(category == RickAndMortyApiSearchKeys.ALL){
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    maxSize = 60,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { DataPagingSourceAll(rickAndMortyApiService) }
            ).liveData
        } else {
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    maxSize = 60,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { DataPagingSourceByStatus(rickAndMortyApiService, category) }
            ).liveData
        }
}