package com.braxton.recipefour.Model

import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>
}