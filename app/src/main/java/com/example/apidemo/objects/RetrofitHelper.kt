package com.example.apidemo.objects

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://api.openai.com/v1/" //we can take in constants
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl( BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
