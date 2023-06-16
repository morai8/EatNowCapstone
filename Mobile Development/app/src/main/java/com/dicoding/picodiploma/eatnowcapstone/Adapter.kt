package com.dicoding.picodiploma.eatnowcapstone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.eatnowcapstone.model.RecipeItem
import android.content.Context
import android.view.View
import android.widget.TextView
import com.dicoding.picodiploma.eatnowcapstone.databinding.ItemRowIngredientsBinding
import org.w3c.dom.Text

class Adapter (val recipeItem:MutableList<RecipeItem>):RecyclerView.Adapter<RecipeViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_ingredients,parent,false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipeItem.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        return holder.bindView(recipeItem[position])
    }
}
class RecipeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    private val tvTitle : TextView = itemView.findViewById(R.id.tv_recipeName)
    private val tvCalories : TextView = itemView.findViewById(R.id.tv_cal)

    fun bindView(recipeItem : RecipeItem){
        tvTitle.text = recipeItem.title
        tvCalories.text = recipeItem.calories
    }
}
