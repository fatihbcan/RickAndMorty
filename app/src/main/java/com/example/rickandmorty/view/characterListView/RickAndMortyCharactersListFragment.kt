package com.example.rickandmorty.view.characterListView

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.data.listPageData.ItemListData
import com.example.rickandmorty.databinding.RickAndMortyCharactersListFragmentBinding
import com.example.rickandmorty.viewModel.RickAndMortyCharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RickAndMortyCharactersListFragment : Fragment(R.layout.rick_and_morty_characters_list_fragment) , ItemListPagingAdapter.OnItemClickListener{

    private var _binding : RickAndMortyCharactersListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RickAndMortyCharactersListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backGroundColor()
        _binding = RickAndMortyCharactersListFragmentBinding.bind(view)

        val adapter = ItemListPagingAdapter(this)


        binding.customSearchBar.setOnEditorActionListener { v, actionId, event ->
            when(actionId){
                EditorInfo.IME_ACTION_SEARCH -> {
                    if(binding.customSearchBar.getSearchedText().length >= 2){
                        viewModel.searchItems(binding.categories.position, binding.customSearchBar.getSearchedText())
                    } else {
                        viewModel.searchItems(binding.categories.position, "")
                    }
                    true
                }
                else -> false
            }
        }

        binding.categories.setOnPositionChangedListener { position ->
            if(binding.customSearchBar.getSearchedText().length >= 2){
                viewModel.searchItems(position, binding.customSearchBar.getSearchedText())
            } else {
                viewModel.searchItems(position, "")
            }
            binding.recyclerView.scrollToPosition(0)
        }

        //sets recycleView
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = ItemListLoadStateAdapter { adapter.retry() },
                footer = ItemListLoadStateAdapter { adapter.retry() }
            )
        }

        //observes paging data and submit it to paging adapter
        viewModel.listItems.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: ItemListData) {
        val action = RickAndMortyCharactersListFragmentDirections.goToDetailsFragment(item)
        findNavController().navigate(action)
    }

    //handles all fragment color including status bar and navigation bar
    private fun backGroundColor() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.navigationBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        activity?.window?.setBackgroundDrawableResource(R.drawable.amber200_to_orange400_gradient)
    }
}