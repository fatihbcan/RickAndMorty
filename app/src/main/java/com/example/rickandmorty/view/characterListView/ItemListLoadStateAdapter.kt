package com.example.rickandmorty.view.characterListView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LoadStateFooterBinding
import retrofit2.HttpException

class ItemListLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ItemListLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class LoadStateViewHolder(private val binding: LoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke() //unit function to append a functionality later
            }
        }

        // according to loading state shows spinner or button with text
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                if (loadState.error is HttpException) {
                    binding.apply {
                        loadingSpinner.isVisible = false
                        buttonRetry.isVisible = false
                        textViewError.isVisible = false
                    }
                }
            } else {
                binding.apply {
                    loadingSpinner.isVisible = loadState is LoadState.Loading
                    buttonRetry.isVisible = loadState !is LoadState.Loading
                    textViewError.isVisible = loadState !is LoadState.Loading
                }
            }
        }
    }
}