package com.braxton.recipefour.View

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.braxton.recipefour.Model.GetDataService
import com.braxton.recipefour.Model.MealResponse
import com.braxton.recipefour.Model.RetroClientInstance
import com.braxton.recipefour.R
import com.braxton.recipefour.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    var youtubelink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id = intent.getStringExtra("id")

        getSpecificItem(id!!)

        binding.imgToolbarBtnBack.setOnClickListener {
            finish()
        }

        binding.btnYouTube.setOnClickListener {
            var uri = Uri.parse(youtubelink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    private fun getSpecificItem(id: String) {
        val service = RetroClientInstance.retrofitInstance!!.create(GetDataService::class.java)
        val call = service.getSpecificItem(id)

        call.enqueue(object: Callback<MealResponse> {

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
               Glide.with(this@DetailActivity).load(response.body()!!.mealsEntity[0].strmealthumb).into(binding.imgItem)

                binding.tvCategory.text = response.body()!!.mealsEntity[0].strmeal

                var ingredient = "${response.body()!!.mealsEntity[0].stringredient1}  ${response.body()!!.mealsEntity[0].strmeasure1}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient2}  ${response.body()!!.mealsEntity[0].strmeasure2}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient3}  ${response.body()!!.mealsEntity[0].strmeasure3}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient4}  ${response.body()!!.mealsEntity[0].strmeasure4}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient5}  ${response.body()!!.mealsEntity[0].strmeasure5}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient6}  ${response.body()!!.mealsEntity[0].strmeasure6}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient7}  ${response.body()!!.mealsEntity[0].strmeasure7}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient8}  ${response.body()!!.mealsEntity[0].strmeasure8}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient9}  ${response.body()!!.mealsEntity[0].strmeasure9}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient10}  ${response.body()!!.mealsEntity[0].strmeasure10}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient11}  ${response.body()!!.mealsEntity[0].strmeasure11}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient12}  ${response.body()!!.mealsEntity[0].strmeasure12}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient13}  ${response.body()!!.mealsEntity[0].strmeasure13}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient14}  ${response.body()!!.mealsEntity[0].strmeasure14}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient15}  ${response.body()!!.mealsEntity[0].strmeasure15}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient16}  ${response.body()!!.mealsEntity[0].strmeasure16}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient17}  ${response.body()!!.mealsEntity[0].strmeasure17}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient18}  ${response.body()!!.mealsEntity[0].strmeasure18}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient19}  ${response.body()!!.mealsEntity[0].strmeasure19}\n" +
                        "${response.body()!!.mealsEntity[0].stringredient20}  ${response.body()!!.mealsEntity[0].strmeasure20}\n"

                binding.tvIngredients.text = ingredient
                binding.tvInstructions.text = response.body()!!.mealsEntity[0].strinstructions

                youtubelink = response.body()!!.mealsEntity[0].strsource
            }
        })
    }
}