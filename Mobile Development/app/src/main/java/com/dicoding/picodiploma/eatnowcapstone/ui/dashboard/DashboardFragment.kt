package com.dicoding.picodiploma.eatnowcapstone.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.eatnowcapstone.Adapter
import com.dicoding.picodiploma.eatnowcapstone.R
import com.dicoding.picodiploma.eatnowcapstone.api.ApiRecipe
import com.dicoding.picodiploma.eatnowcapstone.api.ApiService
import com.dicoding.picodiploma.eatnowcapstone.databinding.FragmentDashboardBinding
import com.dicoding.picodiploma.eatnowcapstone.model.RecipeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.rvShowRecipe

        val apiRecipe = ApiRecipe.buildService(ApiService::class.java)
        val call = apiRecipe.getRecipe()

        call.enqueue(object : Callback<MutableList<RecipeItem>> {
            override fun onResponse(
                call: Call<MutableList<RecipeItem>>,
                response: Response<MutableList<RecipeItem>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = Adapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<RecipeItem>>, t: Throwable) {
                t.printStackTrace()
                Log.e("Failed", t.message.toString())
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}