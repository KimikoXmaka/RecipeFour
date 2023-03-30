package com.braxton.recipefour.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.braxton.recipefour.ViewModel.MainCategoryAdapter
import com.braxton.recipefour.ViewModel.Recipes
import com.braxton.recipefour.ViewModel.SubCategoryAdapter
import com.braxton.recipefour.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    var arrMainCategory = ArrayList<Recipes>()
    var arrSubCategory = ArrayList<Recipes>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        arrMainCategory.add(Recipes(1, "Beef"))
        arrMainCategory.add(Recipes(2, "Chicken"))
        arrMainCategory.add(Recipes( 3, "Dessert"))
        arrMainCategory.add(Recipes( 4, "Drink"))

        mainCategoryAdapter.setData(arrMainCategory)

        arrSubCategory.add(Recipes( 1, "Meatloaf"))
        arrSubCategory.add(Recipes(2, "Chicken and Rice"))
        arrSubCategory.add(Recipes(3, "Chocolate Cake"))
        arrSubCategory.add(Recipes(4, "Iced Tea"))

        subCategoryAdapter.setData(arrSubCategory)

        binding.rvMainCategory.layoutManager = LinearLayoutManager( this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMainCategory.adapter = mainCategoryAdapter

        binding.rvSubCategory.layoutManager = LinearLayoutManager( this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSubCategory.adapter = subCategoryAdapter
    }
}