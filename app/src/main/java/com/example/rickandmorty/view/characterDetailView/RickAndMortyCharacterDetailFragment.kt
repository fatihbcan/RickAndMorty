package com.example.rickandmorty.view.characterDetailView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.RickAndMortyCharacterDetailFragmentBinding
import com.example.rickandmorty.databinding.RickAndMortyCharactersListFragmentBinding
import com.example.rickandmorty.viewModel.RickAndMortyCharacterDetailViewModel
import com.example.rickandmorty.viewModel.RickAndMortyCharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RickAndMortyCharacterDetailFragment: Fragment(R.layout.rick_and_morty_character_detail_fragment){

    private var _binding : RickAndMortyCharacterDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: RickAndMortyCharacterDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<RickAndMortyCharacterDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = RickAndMortyCharacterDetailFragmentBinding.bind(view)

        backGroundColor()

        viewModel.loadEpisodes(args.character)

        binding.item = args.character

        viewModel.episodes.observe(viewLifecycleOwner, {
            episodeList ->
            binding.episodeList.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL ,false)
                it.adapter = CharacterEpisodesAdapter(episodeList)
            }
        })
    }

    //handles all fragment color including status bar and navigation bar
    private fun backGroundColor() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.navigationBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.setBackgroundDrawableResource(R.drawable.amber200_to_orange400_gradient)
    }
}