package com.example.apidemo.dataclasses.livescore

import com.example.apidemo.dataclasses.livescore.Ref

data class LiveScoreDataClass(
    val Eid: String,
    val Esd: Long,
    val Refs: List<Ref>,
    val Vneut: Int,
    val Vnm: String,
    val Vsp: Int
)