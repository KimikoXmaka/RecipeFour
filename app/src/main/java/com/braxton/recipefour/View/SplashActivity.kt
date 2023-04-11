package com.braxton.recipefour.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.braxton.recipefour.Model.*
import com.braxton.recipefour.databinding.ActivitySplashBinding
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import kotlin.math.log


class SplashActivity : BaseActivity(), EasyPermissions.RationaleCallbacks, EasyPermissions.PermissionCallbacks {

    private var READ_STORAGE_PERM = 123

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readStorageTask()

        binding.btnGetStarted.setOnClickListener {
            var intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun getCategories() {
        val service = RetroClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object: Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {

                for (arr in response.body()!!.categrieitems!!) {
                    getMeal(arr.strcategory)
                }

                insertDataIntoRoomDb(response.body())

            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
               // loader.visiblity = View.INVISIBLE
                Toast.makeText(this@SplashActivity, "Your a loser", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getMeal(categoryName: String) {
        val service = RetroClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getMealList(categoryName)
        call.enqueue(object : Callback<Meal> {
            override fun onResponse(
                call: Call<Meal>,
                response: Response<Meal>) {

                insertMealDataIntoRoomdb(categoryName, response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {

                binding.loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun insertMealDataIntoRoomdb(categoryName: String, meal: Meal?) {

        launch {
            this.let {
                for(arr in meal!!.mealsItems!!) {
                    var mealItemModel = MealItems(
                        arr.id,
                        arr.idMeal,
                        categoryName,
                        arr.strMeal,
                        arr.strMealThumb
                    )
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .recipeDao().insertMeal(mealItemModel)
                    Log.d("mealData", arr.toString())
                }
                binding.btnGetStarted.visibility = View.VISIBLE
            }
        }
    }

    fun insertDataIntoRoomDb(category: Category?) {
launch {
    this.let {
        RecipeDatabase.getDatabase( this@SplashActivity).recipeDao().clearDb()
        for (arr in category!!.categrieitems!!) {
            RecipeDatabase.getDatabase(this@SplashActivity)
                .recipeDao().insertCategory(arr)
        }
    }
}
    }

    fun clearDataBase() {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()
            }
        }
    }

    private fun hasReadStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun readStorageTask() {
        if (hasReadStoragePermission()) {
            clearDataBase()
            getCategories()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your storage.",
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions,grantResults, this)
    }
    override fun onRationaleAccepted(requestCode: Int) {

    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}