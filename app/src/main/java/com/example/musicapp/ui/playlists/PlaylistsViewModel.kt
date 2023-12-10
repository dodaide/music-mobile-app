package com.example.musicapp.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Playlists Fragment"
    }
    val text: LiveData<String> = _text
}