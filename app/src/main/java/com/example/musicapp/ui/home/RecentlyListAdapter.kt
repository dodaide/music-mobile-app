package com.example.musicapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.ItemRecentlySongBinding

class RecentlyListAdapter() :
    ListAdapter<Songs, RecentlyListAdapter.RecentlyViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyViewHolder {
        return RecentlyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecentlyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class RecentlyViewHolder(
        private val binding: ItemRecentlySongBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(songs: Songs) = binding.apply {
            songs.image?.let { ivBg.setImageResource(it) }
            songs.image?.let { image.setImageResource(it) }
            song.text = songs.songs
            author.text = songs.author
        }

        companion object {
            fun create(
                viewGroup: ViewGroup
            ): RecentlyViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = ItemRecentlySongBinding.inflate(layoutInflater, viewGroup, false)
                return RecentlyViewHolder(binding)
            }
        }
    }

    class EventDiffCallback : DiffUtil.ItemCallback<Songs>() {
        override fun areItemsTheSame(oldItem: Songs, newItem: Songs): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Songs, newItem: Songs): Boolean {
            return oldItem == newItem
        }
    }

}