package com.example.musicapp.ui.songs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SongsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Songs Fragment"
    }
    val text: LiveData<String> = _text
}