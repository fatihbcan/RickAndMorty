package com.example.rickandmorty.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.data.ItemListData
import com.example.rickandmorty.databinding.RickAndMortyCharactersListFragmentBinding
import com.example.rickandmorty.view.characterListView.ItemListPagingAdapter
import com.example.rickandmorty.viewModel.RickAndMortyCharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RickAndMortyCharacterDetailFragment: Fragment(R.layout.rick_and_morty_character_detail_fragment){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backGroundColor()
    }

    //handles all fragment color including status bar and navigation bar
    private fun backGroundColor() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.navigationBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.setBackgroundDrawableResource(R.drawable.amber200_to_orange400_gradient)
    }
}