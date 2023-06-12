package com.dicoding.picodiploma.eatnowcapstone

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.eatnowcapstone.api.ApiConfig
import com.dicoding.picodiploma.eatnowcapstone.api.RegisterRequest
import com.dicoding.picodiploma.eatnowcapstone.api.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel:ViewModel() {
    val registerResponse_ = MutableLiveData<RegisterResponse>()
    fun registerCek(name : String,email : String,password : String, confPassword: String){
        val registerRequest =RegisterRequest(name,email,password,confPassword)
        ApiConfig.getApi().registerAcc(registerRequest)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    registerResponse_.postValue(response.body())
                    Log.d("Berhasil ", "onResponse: ${response.body()}")
                }
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("Gagal", "onFailure: ${t.message}")
                }
            })
    }
}