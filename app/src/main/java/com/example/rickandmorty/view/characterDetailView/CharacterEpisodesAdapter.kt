package com.example.rickandmorty.view.characterDetailView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.detailPageData.Episode
import com.example.rickandmorty.databinding.RecyclerViewEpisodeListItemBinding
import com.example.rickandmorty.databinding.RecyclerViewItemBinding

class CharacterEpisodesAdapter(private val episodeList: List<Episode>) :
    RecyclerView.Adapter<CharacterEpisodesAdapter.EpisodeListViewHolder>() {

    inner class EpisodeListViewHolder(private val episodeListItemBinding: RecyclerViewEpisodeListItemBinding) :
        RecyclerView.ViewHolder(episodeListItemBinding.root) {

            fun bind(episode: Episode){
                episodeListItemBinding.item = episode;
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EpisodeListViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_episode_list_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: EpisodeListViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    override fun getItemCount(): Int = episodeList.size
}