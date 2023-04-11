package com.braxton.recipefour.Model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealListConverter {

    @TypeConverter
    fun fromCategoryList(category: List<MealItems>): String? {
        if (category == null) {
            return (null)
        } else {
            val gson = Gson()
            val type = object  : TypeToken<MealItems>() {

            }.type
            return gson.toJson(category, type)
        }
    }

    @TypeConverter
    fun toCategoryList(categoryString: String): List<MealItems>? {
        if (categoryString == null) {
            return (null)
        } else {
            val gson = Gson()
            val type = object  : TypeToken<MealItems>()  {

            }.type
            return gson.fromJson(categoryString, type)
        }
    }
}