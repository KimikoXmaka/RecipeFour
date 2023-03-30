package com.braxton.recipefour.ViewModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    suspend fun getAllCategory(): List<Recipes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryItems: Recipes)
}