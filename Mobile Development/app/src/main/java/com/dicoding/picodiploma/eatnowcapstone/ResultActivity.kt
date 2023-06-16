package com.dicoding.picodiploma.eatnowcapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.eatnowcapstone.api.ApiRecipe
import com.dicoding.picodiploma.eatnowcapstone.api.ApiService
import com.dicoding.picodiploma.eatnowcapstone.model.RecipeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_recipe)

        val apiRecipe  = ApiRecipe.buildService(ApiService::class.java)
        val call = apiRecipe.getRecipe()

        call.enqueue(object : Callback<MutableList<RecipeItem>> {
            override fun onResponse(call: Call<MutableList<RecipeItem>>, response: Response<MutableList<RecipeItem>>) {
                if(response.isSuccessful){
                   recyclerView.apply {
                       layoutManager = LinearLayoutManager(this@ResultActivity)
                       adapter= Adapter(response.body()!!)
                   }
                }
            }

            override fun onFailure(call: Call<MutableList<RecipeItem>>, t: Throwable) {
                t.printStackTrace()
                Log.e("Failed", t.message.toString() )
            }

        })
    }
}