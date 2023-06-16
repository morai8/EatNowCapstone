package com.dicoding.picodiploma.eatnowcapstone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.eatnowcapstone.model.RecipeItem
import com.dicoding.picodiploma.eatnowcapstone.databinding.ItemRowIngredientsBinding

class Adapter(private val recipeItems: MutableList<RecipeItem>) :
    RecyclerView.Adapter<Adapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRowIngredientsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindView(recipeItems[position])
    }

    override fun getItemCount(): Int {
        return recipeItems.size
    }

    inner class RecipeViewHolder(private val binding: ItemRowIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(recipeItem: RecipeItem) {
            binding.tvRecipeName.text = recipeItem.title
            binding.tvCal.text = recipeItem.calories
        }
    }
}
