package com.example.apidemo.dataclasses.dummyEMP

data class DummyDataClass(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<Users>
)