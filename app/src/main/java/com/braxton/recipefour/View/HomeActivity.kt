package com.braxton.recipefour.View

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.braxton.recipefour.Model.CategoryItems
import com.braxton.recipefour.Model.MealItems
import com.braxton.recipefour.Model.RecipeDatabase
import com.braxton.recipefour.ViewModel.MainCategoryAdapter
import com.braxton.recipefour.ViewModel.SubCategoryAdapter
import com.braxton.recipefour.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromDb()

        mainCategoryAdapter.setClickListener(onClicked)
        subCategoryAdapter.setClickListener(onClickedSubItem)
    }

    private fun getDataFromDb() {
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()
                mainCategoryAdapter.setData(arrMainCategory)

                getMealDatafromDb(arrMainCategory[0].strcategory)

                binding.rvMainCategory.layoutManager = LinearLayoutManager( this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.rvMainCategory.adapter = mainCategoryAdapter
            }
        }
    }

    private val onClicked = object: MainCategoryAdapter.OnItemClickListener {
        override fun onClicked(categoryName: String) {
            getMealDatafromDb(categoryName)
        }
    }

    private val onClickedSubItem = object: SubCategoryAdapter.OnItemClickListener {
        override fun onClick(id: String) {
            var intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    private fun getMealDatafromDb(categoryName: String) {
        binding.category.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealItems>
                subCategoryAdapter.setData(arrSubCategory)
                binding.rvSubCategory.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                binding.rvSubCategory.adapter = subCategoryAdapter
            }
        }
    }
}