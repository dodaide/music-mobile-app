package com.example.musicapp.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private var recentlyListAdapter: RecentlyListAdapter? = null
    private var albumsAdapter: AlbumsListAdapter? = null
    private var artistsAdapter: ArtistsListAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recentlyListAdapter = RecentlyListAdapter()
        binding.rcvSong.adapter = recentlyListAdapter

        val listRecentSong = getRecentSong()
        recentlyListAdapter?.submitList(listRecentSong)


        albumsAdapter = AlbumsListAdapter()

        with(binding.rcvAlbums) {
            val spacing = context.resources.getDimension(R.dimen.design_8_dp)
            setPadding(spacing.toInt(), spacing.toInt(), spacing.toInt(), spacing.toInt())
            clipToPadding = false
            clipChildren = false
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override
                fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.set(
                        spacing.toInt(),
                        spacing.toInt(),
                        context.resources.getDimension(R.dimen.design_10_dp).toInt(),
                        spacing.toInt()
                    )
                }
            })

            adapter = albumsAdapter
        }

        val listAlbums = getListAlbums()
        albumsAdapter?.submitList(listAlbums)


        artistsAdapter = ArtistsListAdapter()

        with(binding.rcvArtists) {
            val spacing = context.resources.getDimension(R.dimen.design_8_dp)
            setPadding(spacing.toInt(), spacing.toInt(), spacing.toInt(), spacing.toInt())
            clipToPadding = false
            clipChildren = false
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override
                fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.set(
                        spacing.toInt(),
                        spacing.toInt(),
                        context.resources.getDimension(R.dimen.design_10_dp).toInt(),
                        spacing.toInt()
                    )
                }
            })

            adapter = artistsAdapter
        }

        val listArtists = getListArtists()
        artistsAdapter?.submitList(listArtists)
    }

    private fun getRecentSong(): MutableList<Songs> {
        val list = mutableListOf<Songs>()
        list.add(Songs(1, "Ghot of you", "5 Second of Summer", R.drawable.image_default))
        list.add(Songs(2, "Ghot of you", "5 Second of Summer", R.drawable.image_default))
        list.add(Songs(3, "Ghot of you", "5 Second of Summer", R.drawable.image_default))
        list.add(Songs(4, "Ghot of you", "5 Second of Summer", R.drawable.image_default))
        return list
    }

    private fun getListAlbums(): MutableList<Albums> {
        val list = mutableListOf<Albums>()
        list.add(Albums(1, R.drawable.image_default))
        list.add(Albums(2, R.drawable.image_default))
        list.add(Albums(3, R.drawable.image_default))
        list.add(Albums(4, R.drawable.image_default))
        list.add(Albums(5, R.drawable.image_default))
        list.add(Albums(6, R.drawable.image_default))
        return list
    }

    private fun getListArtists(): MutableList<Artists> {
        val list = mutableListOf<Artists>()
        list.add(Artists(1, R.drawable.image_default))
        list.add(Artists(2, R.drawable.image_default))
        list.add(Artists(3, R.drawable.image_default))
        list.add(Artists(4, R.drawable.image_default))
        list.add(Artists(5, R.drawable.image_default))
        list.add(Artists(6, R.drawable.image_default))
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}