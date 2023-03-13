package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.dummyEMP.DummyDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DummyDataInterface {

    @Headers("Content-Type: application/json")
    @GET("users")
    fun getEmpDetails(
        @Query("limit") limit: Int
    ): Call<DummyDataClass>

}