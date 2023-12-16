package com.example.musicapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.ItemAlbumsBinding

class AlbumsListAdapter() :
    ListAdapter<Albums, AlbumsListAdapter.AlbumsViewHolder>(EventDiffCallback()) {

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
        fun bind(albums: Albums) = binding.apply {
            albums.image?.let { image.setImageResource(it) }
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

    class EventDiffCallback : DiffUtil.ItemCallback<Albums>() {
        override fun areItemsTheSame(oldItem: Albums, newItem: Albums): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Albums, newItem: Albums): Boolean {
            return oldItem == newItem
        }
    }

}