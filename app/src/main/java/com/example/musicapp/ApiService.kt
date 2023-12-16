package com.example.musicapp

import com.example.musicapp.ui.musicplayer.SongInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("songs/{id}")
    fun getSongInfo(@Path("id") songId: String): Call<SongInfoResponse>
}