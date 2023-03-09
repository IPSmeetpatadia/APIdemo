package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.boomerang.BoomerangApiDataClass
import com.example.apidemo.modelclass.BoomerangModelClass2
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

interface BoomerangApiInterface {

    @PUT("query-json")
    fun getData(
        @Query("data") data: String,
        @Body modelClass2: BoomerangModelClass2,
        @Header("X-RapidAPI-Key") apiKey: String,
        @Header("X-RapidAPI-Host") apiHost: String
    ): Call<BoomerangApiDataClass>
}