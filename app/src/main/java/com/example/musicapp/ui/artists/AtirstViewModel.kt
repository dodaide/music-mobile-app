package com.example.musicapp.ui.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AtirstViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Artists Fragment"
    }
    val text: LiveData<String> = _text
}