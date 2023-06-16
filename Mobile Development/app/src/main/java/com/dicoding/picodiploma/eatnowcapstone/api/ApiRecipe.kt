package com.dicoding.picodiploma.eatnowcapstone.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRecipe {
    private val client= OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://34.128.109.218:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T>buildService(service:Class<T>):T{
        return retrofit.create(service)
    }
}