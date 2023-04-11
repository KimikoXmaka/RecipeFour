package com.braxton.recipefour.ViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.braxton.recipefour.Model.CategoryItems
import com.braxton.recipefour.R
import com.bumptech.glide.Glide


class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var arrmainCategory = ArrayList<CategoryItems>()
    var ctx: Context? = null

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvDishName: TextView = itemView.findViewById(R.id.tv_dish_name)
        val imgDishName: ImageView = itemView.findViewById(R.id.img_dish)

    }

    fun setData(arrData: ArrayList<CategoryItems>) {
        arrmainCategory = arrData as ArrayList<CategoryItems>
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        Glide.with(ctx!!).load(arrmainCategory[position].strcategorythumb).into(holder.imgDishName)
        holder.tvDishName.text = arrmainCategory[position].strcategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_vategory, parent, false))
    }

    override fun getItemCount(): Int {
       return arrmainCategory.size
    }
}