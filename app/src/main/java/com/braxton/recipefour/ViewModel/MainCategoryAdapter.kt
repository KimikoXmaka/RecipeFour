package com.braxton.recipefour.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.braxton.recipefour.R


class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var arrmainCategory = ArrayList<Recipes>()

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    fun setData(arrData: List<Recipes>) {
        arrmainCategory = arrData as ArrayList<Recipes>
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val tv_dish_name: TextView = holder.itemView.findViewById(R.id.tv_dish_name)
        tv_dish_name.text = arrmainCategory[position].dishName
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_vategory, parent, false))
    }

    override fun getItemCount(): Int {
       return arrmainCategory.size
    }
}