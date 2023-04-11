package com.braxton.recipefour.Model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealResponse(
    @Expose
    @SerializedName("meals")
    var mealsEntity: List<MealsEntity>
)

data class MealsEntity(
    @Expose
    @SerializedName("idMeal")
    val idmeal: String,

    @Expose
    @SerializedName("strMeal")
    val strmeal: String,

    @Expose
    @SerializedName("strCategory")
    val strcategory: String,

    @Expose
    @SerializedName("strArea")
    val strarea: String,

    @Expose
    @SerializedName("strInstructions")
    val strinstructions: String,

    @Expose
    @SerializedName("strMealThumb")
    val strmealthumb: String,
)
