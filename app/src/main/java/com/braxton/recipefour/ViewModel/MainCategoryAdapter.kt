package com.braxton.recipefour.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.braxton.recipefour.R
import kotlinx.android.synthetic.main.item_rv_main_vategory.view.*
import kotlinx.coroutines.NonDisposableHandle.parent

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var arrmainCategory = ArrayList<Recipes>()

    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    fun setData(arrData: List<Recipes>) {
        arrmainCategory = arrData as ArrayList<Recipes>
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
holder.itemView.tv_dish_name.text = arrmainCategory[position].dishName
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_vategory, parent, false))
    }

    override fun getItemCount(): Int {
       return arrmainCategory.size
    }
}