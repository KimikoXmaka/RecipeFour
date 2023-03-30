package com.braxton.recipefour.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.braxton.recipefour.ViewModel.RecipeDao
import com.braxton.recipefour.ViewModel.Recipes

@Database(entities = [Recipes::class, CategoryItems::class, Category::class], version = 1, exportSchema = false)
@TypeConverters(CategoryListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object {

        var recipeDatabase: RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
            if (recipeDatabase == null) {
                recipeDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipeDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}