package com.braxton.recipefour.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "categoryItems")
    @Expose
    @SerializedName("categories")
    var  categrieitems: List<CategoryItems>? = null
)