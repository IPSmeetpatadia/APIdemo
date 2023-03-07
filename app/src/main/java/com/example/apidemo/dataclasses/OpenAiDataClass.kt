package com.example.apidemo.dataclasses

/*
                          INCOMPLETE
*/

data class OpenAiDataClass(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: Usage
)