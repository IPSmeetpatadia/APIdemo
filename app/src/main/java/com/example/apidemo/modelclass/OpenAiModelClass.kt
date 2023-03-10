package com.example.apidemo.modelclass

import com.google.gson.annotations.SerializedName

class OpenAiModelClass(

    @SerializedName("model") val model: String,
    @SerializedName("prompt") val prompt: String,
    @SerializedName("temperature") val temperature: Int,
    @SerializedName("max_tokens") val max_tokens: Int,
    @SerializedName("top_p") val top_p: Int,
    @SerializedName("frequency_penalty") val frequency_penalty: Int,
    @SerializedName("presence_penalty") val presence_penalty: Int

)