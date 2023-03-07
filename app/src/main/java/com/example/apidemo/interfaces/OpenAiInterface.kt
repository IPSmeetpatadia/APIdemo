package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.OpenAiDataClass
import com.example.apidemo.modelclass.OpenAiModelClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/*
                          INCOMPLETE
*/

interface OpenAiInterface {

    @POST("completions")
    fun postQuery(
        @Header("Content-Type") type: String,
        @Header("Authorization") token: String,
        @Body openAiModelClass: OpenAiModelClass
    ): Call<List<OpenAiDataClass>>

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