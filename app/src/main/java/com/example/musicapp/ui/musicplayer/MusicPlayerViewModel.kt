package com.example.musicapp.ui.musicplayer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicPlayerViewModel : ViewModel() {
    private val _songId = MutableLiveData<String>()
    val songId: LiveData<String> get() = _songId

    private val _currentSongUrl = MutableLiveData<String>()
    val currentSongUrl: LiveData<String> get() = _currentSongUrl

    private val _imgUrl = MutableLiveData<String>()
    val imgUrl: LiveData<String> get() = _imgUrl

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
        _songId.value = ""
        _songName.value = ""
        _artist.value = ""
        _isRepeatOn.value = false
        _isShuffleOn.value = false
        _isPlaying.value = true
    }

    fun fetchSongInfo(id: String) {
        val call = RetrofitInstance.api.getSongInfo(id)

        call.enqueue(object : Callback<SongInfoResponse> {
            override fun onResponse(call: Call<SongInfoResponse>, response: Response<SongInfoResponse>) {
                if (response.isSuccessful) {
                    val songInfo = response.body()
                    songInfo?.let {
                        // Cập nhật thông tin trong ViewModel
                        setSongId(it.SongId)
                        setImgUrl(it.ImageUrl)
                        setCurrentSongUrl(it.AudioUrl)
                        setSongName(it.SongName)
                        setArtistName(it.SingerName)
                    }
                }
            }

            override fun onFailure(call: Call<SongInfoResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun setSongId(id: String) {
        _songId.value = id
    }

    fun setCurrentSongUrl(url: String) {
        _currentSongUrl.value = url
    }

    fun setImgUrl(url: String) {
        _imgUrl.value = url
    }

    fun setSongName(name: String) {
        _songName.value = name
    }

    fun setArtistName(name: String) {
        _artist.value = name
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