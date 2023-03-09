package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.TranslationDataClass
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface TranslationInterface {

    @FormUrlEncoded
    @POST("translate")
    fun getTranslatedText(
        @Field("to") to: String,
        @Field("text") text: String,
        @Field("from") from: String,
        @Header("X-RapidAPI-Key") apiKey: String,
        @Header("X-RapidAPI-Host") apiHost: String
    ): Call<TranslationDataClass>

}