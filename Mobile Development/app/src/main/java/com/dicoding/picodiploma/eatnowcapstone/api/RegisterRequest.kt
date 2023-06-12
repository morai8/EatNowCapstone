package com.dicoding.picodiploma.eatnowcapstone.api

interface RegisterRequest {

    val name: String,

    val email: String,

    val password: String,

    val confPassword: String
}