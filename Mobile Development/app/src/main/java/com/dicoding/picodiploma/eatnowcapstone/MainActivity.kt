package com.dicoding.picodiploma.eatnowcapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.picodiploma.eatnowcapstone.api.ApiConfig
import com.dicoding.picodiploma.eatnowcapstone.api.ApiRecipe
import com.dicoding.picodiploma.eatnowcapstone.api.ApiService
import com.dicoding.picodiploma.eatnowcapstone.model.Recipe
import com.dicoding.picodiploma.eatnowcapstone.model.RecipeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}