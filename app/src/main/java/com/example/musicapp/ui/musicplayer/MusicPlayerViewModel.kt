package com.example.musicapp.ui.musicplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MusicPlayerViewModel : ViewModel() {
    private val _currentSongUrl = MutableLiveData<String>()
    val currentSongUrl: LiveData<String> get() = _currentSongUrl

    private val _songName = MutableLiveData<String>()
    val songName: LiveData<String> get() = _songName

    private val _artist = MutableLiveData<String>()
    val artist: LiveData<String> get() = _artist

    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    private val _isRepeatOn = MutableLiveData<Boolean>()
    val isRepeatOn: LiveData<Boolean> get() = _isRepeatOn

    private val _isShuffleOn = MutableLiveData<Boolean>()
    val isShuffleOn: LiveData<Boolean> get() = _isShuffleOn

    init {
        _songName.value = "Sample Song"
        _artist.value = "Sample Artist"
        _isRepeatOn.value = false
        _isShuffleOn.value = false
        _isPlaying.value = true
    }

    fun setCurrentSongUrl(url: String) {
        _currentSongUrl.value = url
    }

    fun togglePlayPause() {
        _isPlaying.value = !_isPlaying.value!!
    }

    fun skipToNext() {
        // Implement logic to skip to the next song
    }

    fun skipToPrevious() {
        // Implement logic to skip to the previous song
    }

    fun toggleRepeat() {
        _isRepeatOn.value = !_isRepeatOn.value!!
    }

    fun toggleShuffle() {
        _isShuffleOn.value = !_isShuffleOn.value!!
    }
}