package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.openai.OpenAiDataClass
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/*
                          INCOMPLETE
*/

interface OpenAiInterface {

    @Headers(
        "Content-Type: application/json",
//        "Authorization: Bearer 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"
    )

    @POST("completions")
    fun postQuery(
        @Body openAiModelClass: JsonObject,
        @Header("Authorization") token: String
    ): Call<OpenAiDataClass>

}

/*

    @Body model: String,
    @Body prompt: String,
    @Body temperature: String,
    @Body max_tokens: String,
    @Body top_p: String,
    @Body frequency_penalty: String,
    @Body presence_penalty: String

*/