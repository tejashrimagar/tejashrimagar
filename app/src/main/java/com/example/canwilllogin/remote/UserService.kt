package com.example.canwilllogin.remote

import com.example.canwilllogin.model.LoginRequest
import com.example.canwilllogin.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService
{
    /*@Headers("Content-Type:application/json")
    @POST("login")
    fun userLogin(@Header("Content-Type")content_type : String,@Header("IMSI")imsi : String,@Header("IMEI")imei : String,
        @Body loginResuest: LoginRequest?): Call<LoginResponse?>?*/

    @Headers("Content-Type:application/json")
    @POST("login")
    fun userLogin(@Header("Content-Type")content_type : String,@Body loginResuest: LoginRequest?): Call<LoginResponse?>?

}