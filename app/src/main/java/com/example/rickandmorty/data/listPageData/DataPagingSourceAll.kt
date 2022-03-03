package com.example.rickandmorty.data.listPageData

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.network.RickAndMortyApiService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class DataPagingSourceAll(
    private val rickAndMortyApiService: RickAndMortyApiService,
    private val searchedString: String
) : PagingSource<Int, ItemListData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemListData> {
        val position = params.key ?: STARTING_PAGE_INDEX // page position default 1

        return try {
            val response = if(searchedString.length >= 2){
                rickAndMortyApiService.getAllCharactersByName(position,searchedString)
            } else {
                rickAndMortyApiService.getAllCharacters(position)
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
        } catch (exception: Exception){
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