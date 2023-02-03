package com.braxton.recipefour.ViewModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipes")
data class Recipes(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "dishName")
    var dishName: String
) : java.io.Serializable