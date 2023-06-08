package com.dicoding.picodiploma.eatnowcapstone.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private var token:String?=null
    fun getApi():ApiService{
        val loginInterceptor=
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val authenticatorInterceptor= Interceptor{ chain->
            val req = chain.request()
            if(token!=null){
                val reqHeaders=req.newBuilder()
                    .addHeader("Authorization","Bearer $token")
                    .build()
                chain.proceed(reqHeaders)
            }else{
                chain.proceed(req)
            }
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
            .addInterceptor(authenticatorInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://74c5-113-11-180-109.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
    fun setToken(token:String?) {
        this.token=token
    }
}