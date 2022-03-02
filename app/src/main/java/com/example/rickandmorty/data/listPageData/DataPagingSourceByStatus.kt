package com.example.rickandmorty.data.listPageData

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.rickandmorty.network.RickAndMortyApiService
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class DataPagingSourceByStatus(
    private val rickAndMortyApiService: RickAndMortyApiService,
    private val category: String,
    private val searchedString: String
) : PagingSource<Int, ItemListData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemListData> {
        val position = params.key ?: STARTING_PAGE_INDEX // page position default 1

        return try {
            val response = if(searchedString.length >= 2){
                rickAndMortyApiService.getCharactersByStatusAndName(position, searchedString, category)
            } else {
                rickAndMortyApiService.getCharactersByStatus(position, category)
                // api call
            }
            val resultList = response.results // get api call results

            LoadResult.Page(
                data = resultList,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (resultList.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemListData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}