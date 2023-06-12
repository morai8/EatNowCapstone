package com.dicoding.picodiploma.eatnowcapstone.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("/register")
    fun registerAcc(
    @Body registerRequest : RegisterRequest
    ): Call<RegisterResponse>
}