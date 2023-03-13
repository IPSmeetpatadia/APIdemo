package com.example.apidemo.interfaces

import com.example.apidemo.dataclasses.dummyEMP.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface UserDetailsInterface {

    @GET
    fun getDetailsOfUser(@Url userID: String): Call<Users>

}