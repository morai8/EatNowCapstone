package com.dicoding.picodiploma.eatnowcapstone.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.eatnowcapstone.Adapter
import com.dicoding.picodiploma.eatnowcapstone.api.ApiRecipe
import com.dicoding.picodiploma.eatnowcapstone.api.ApiService
import com.dicoding.picodiploma.eatnowcapstone.databinding.FragmentDashboardBinding
import com.dicoding.picodiploma.eatnowcapstone.model.RecipeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var originalRecipeItems: MutableList<RecipeItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.rvShowRecipe
        searchView = binding.svIngredients

        val apiRecipe = ApiRecipe.buildService(ApiService::class.java)
        val call = apiRecipe.getRecipe()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        call.enqueue(object : Callback<MutableList<RecipeItem>> {
            override fun onResponse(
                call: Call<MutableList<RecipeItem>>,
                response: Response<MutableList<RecipeItem>>
            ) {
                if (response.isSuccessful) {
                    originalRecipeItems = response.body()!!
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = Adapter(originalRecipeItems)
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

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = originalRecipeItems.filter {
                it.title?.toLowerCase(Locale.ROOT)!!.contains(query.toLowerCase(Locale.ROOT))
            }.toMutableList()

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show()
            }

            recyclerView.adapter = Adapter(filteredList)
        } else {
            recyclerView.adapter = Adapter(originalRecipeItems)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
