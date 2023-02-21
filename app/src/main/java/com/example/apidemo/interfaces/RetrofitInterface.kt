package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.RetrofitDataClassItem
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("posts")
    fun getData(): Call<List<RetrofitDataClassItem>>
}