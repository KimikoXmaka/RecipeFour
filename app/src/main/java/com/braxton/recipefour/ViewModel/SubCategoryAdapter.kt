package com.braxton.recipefour.ViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.braxton.recipefour.Model.MealItems
import com.braxton.recipefour.R
import com.bumptech.glide.Glide


class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {

    var arrSubCategory = ArrayList<MealItems>()
    var ctx: Context? = null
    var listener: SubCategoryAdapter.OnItemClickListener? = null

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tvDishName: TextView = itemView.findViewById(R.id.tv_dish_name)
        val dishImage: ImageView = itemView.findViewById(R.id.img_dish)
    }

    fun setData(arrData: ArrayList<MealItems>) {
        arrSubCategory = arrData as ArrayList<MealItems>
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
       holder.tvDishName.text = arrSubCategory[position].strMeal

        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb).into(holder.dishImage)

        holder.itemView.rootView.setOnClickListener {
            listener!!.onClick(arrSubCategory[position].idMeal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.it_rv_sub_category, parent, false))
    }

    override fun getItemCount(): Int {
       return arrSubCategory.size
    }

    fun setClickListener(listener1: SubCategoryAdapter.OnItemClickListener) {
        listener = listener1
    }
    interface OnItemClickListener {
        fun onClick(id: String)
    }
}