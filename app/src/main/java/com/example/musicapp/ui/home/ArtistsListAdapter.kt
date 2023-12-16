package com.example.musicapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.ItemAlbumsBinding

class ArtistsListAdapter() :
    ListAdapter<Artists, ArtistsListAdapter.AlbumsViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return AlbumsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class AlbumsViewHolder(
        private val binding: ItemAlbumsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(artists: Artists) = binding.apply {
            artists.image?.let { image.setImageResource(it) }
        }

        companion object {
            fun create(
                viewGroup: ViewGroup
            ): AlbumsViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = ItemAlbumsBinding.inflate(layoutInflater, viewGroup, false)
                return AlbumsViewHolder(binding)
            }
        }
    }

    class EventDiffCallback : DiffUtil.ItemCallback<Artists>() {
        override fun areItemsTheSame(oldItem: Artists, newItem: Artists): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Artists, newItem: Artists): Boolean {
            return oldItem == newItem
        }
    }

}