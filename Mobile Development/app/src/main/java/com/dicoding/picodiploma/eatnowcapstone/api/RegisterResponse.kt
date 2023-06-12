package com.dicoding.picodiploma.eatnowcapstone.api

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
	@field:SerializedName("status")
	val status: String,
	@field:SerializedName("message")
	val message: String
)
