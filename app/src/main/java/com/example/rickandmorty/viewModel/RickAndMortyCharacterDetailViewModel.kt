package com.example.rickandmorty.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DataRepository
import com.example.rickandmorty.data.detailPageData.Episode
import com.example.rickandmorty.data.listPageData.ItemListData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyCharacterDetailViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {
    // TODO: Implement the ViewModel

    val episodes: MutableLiveData<List<Episode>> = MutableLiveData(listOf())

    fun loadEpisodes(character: ItemListData){
        viewModelScope.launch{
            episodes.value = repository.getCharacterIncludedEpisodeDetails(getIncludedEpisodeList(character))
        }
    }

    private fun getIncludedEpisodeList(character : ItemListData) : String {
        val stringBuilder = StringBuilder()
        val episodeStarterUrl = "https://rickandmortyapi.com/api/episode/"
        for (episode in character.episodeList){
            val subString = episode.substring(episodeStarterUrl.length) + ","
            stringBuilder.append(subString)
        }


        return stringBuilder.toString().substring(0, stringBuilder.toString().length-1)

    }

}