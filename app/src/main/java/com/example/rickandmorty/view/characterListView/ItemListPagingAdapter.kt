package com.example.rickandmorty.view.characterListView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.listPageData.ItemListData
import com.example.rickandmorty.databinding.RecyclerViewItemBinding

class ItemListPagingAdapter (private val listener: OnItemClickListener) :
    PagingDataAdapter<ItemListData, ItemListPagingAdapter.ItemListPagingViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListPagingViewHolder {
        // implements binding
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemListPagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListPagingViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class ItemListPagingViewHolder (private val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // implements onClickListener
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(item: ItemListData) {
            binding.item = item
        }
    }


    interface OnItemClickListener {
        fun onItemClick(item: ItemListData)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ItemListData>() {
            override fun areItemsTheSame(oldItem: ItemListData, newItem: ItemListData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ItemListData, newItem: ItemListData) =
                oldItem == newItem
        }
    }
}