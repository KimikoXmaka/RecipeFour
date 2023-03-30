package com.braxton.recipefour.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.braxton.recipefour.R


class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {

    var arrSubCategory = ArrayList<Recipes>()

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    fun setData(arrData: List<Recipes>) {
        arrSubCategory = arrData as ArrayList<Recipes>
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val tv_dish_name: TextView = holder.itemView.findViewById(R.id.tv_dish_name)
        tv_dish_name.text = arrSubCategory[position].dishName
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.it_rv_sub_category, parent, false))
    }

    override fun getItemCount(): Int {
       return arrSubCategory.size
    }
}