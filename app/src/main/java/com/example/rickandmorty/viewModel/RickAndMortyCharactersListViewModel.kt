package com.example.rickandmorty.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.DataRepository
import com.example.rickandmorty.network.RickAndMortyApiSearchKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RickAndMortyCharactersListViewModel  @Inject constructor(private val repository: DataRepository): ViewModel() {

    // query is a live data
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    // switchMap used to listen live data query and on every change of it, it will perform new api call
    val listItems = currentQuery.switchMap { query ->
        repository.getSearchResults(query.first, query.second).cachedIn(viewModelScope)
    }

    // changes query instead of creating data class, pair used to pass search key and category
    fun searchItems(categoryIndex : Int, searchedString: String) {
        currentQuery.value = Pair(getCategoryName(categoryIndex), searchedString)
    }

    private fun getCategoryName(value : Int) : String {
        return when(value){
            0 -> RickAndMortyApiSearchKeys.ALL
            1 -> RickAndMortyApiSearchKeys.ALIVE
            2 -> RickAndMortyApiSearchKeys.DEAD
            3 -> RickAndMortyApiSearchKeys.UNKNOWN
            else -> RickAndMortyApiSearchKeys.ALL
        }
    }

    companion object {
        private val DEFAULT_QUERY = Pair(RickAndMortyApiSearchKeys.ALL, "")
    }
}