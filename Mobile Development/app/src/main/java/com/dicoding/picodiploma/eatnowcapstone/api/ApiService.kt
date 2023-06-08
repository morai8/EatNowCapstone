package com.dicoding.picodiploma.eatnowcapstone.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun registerAcc(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("confPassword") confPassword:String
    ): Call<RegisterResponse>
}