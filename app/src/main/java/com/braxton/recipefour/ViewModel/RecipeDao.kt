package com.braxton.recipefour.ViewModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.braxton.recipefour.Model.Category
import com.braxton.recipefour.Model.CategoryItems


@Dao
interface RecipeDao {

    @Query("SELECT * FROM category ORDER BY id DESC")
    suspend fun getAllCategory(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryItems: CategoryItems)

    @Query("DELETE FROM categoryitems")
    suspend fun clearDb()
}