package com.example.apidemo.dataclasses

data class LiveScoreDataClass(
    val Eid: String,
    val Esd: Long,
    val Refs: List<Ref>,
    val Vneut: Int,
    val Vnm: String,
    val Vsp: Int
)