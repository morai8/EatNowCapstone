package com.dicoding.picodiploma.eatnowcapstone

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.eatnowcapstone.api.ApiConfig
import com.dicoding.picodiploma.eatnowcapstone.api.RegisterResponse
import com.dicoding.picodiploma.eatnowcapstone.api.SignInRequest
import com.dicoding.picodiploma.eatnowcapstone.api.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel: ViewModel() {
    val signInResponse_ = MutableLiveData<SignInResponse>()
    fun signInCek(email : String,password : String){
        val signInRequest = SignInRequest(email,password)
        ApiConfig.getApi().signInAcc(signInRequest)
            .enqueue(object : Callback<SignInResponse> {
                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ){
                    signInResponse_.postValue(response.body())
                    Log.d("Berhasil", "onResponse: ${response.body()}")
                }
                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    Log.d("Gagal", "onFailure: ${t.message}")
                }
            })
    }
}