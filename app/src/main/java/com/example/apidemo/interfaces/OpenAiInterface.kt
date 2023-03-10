package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.openai.OpenAiDataClass
import com.example.apidemo.modelclass.OpenAiModelClass
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiInterface {

    @POST("completions")
    fun postQuery(
        @Body openAiModelClass: OpenAiModelClass,
        @Header("Authorization") token: String
    ): Call<OpenAiDataClass>

}
