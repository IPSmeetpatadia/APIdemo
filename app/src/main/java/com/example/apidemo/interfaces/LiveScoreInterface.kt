package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.livescore.LiveScoreDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LiveScoreInterface {

    @GET("get-info")
    fun getLiveScore(
        @Query("Category") category: String,
        @Query("Eid") eid: Int,
        @Header("X-RapidAPI-Key") apiKey: String,
        @Header("X-RapidAPI-Host") apiHost: String
    ): Call<LiveScoreDataClass>
}