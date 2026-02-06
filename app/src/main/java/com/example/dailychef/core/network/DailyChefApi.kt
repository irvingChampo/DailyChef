package com.example.dailychef.core.network

import com.example.dailychef.features.DailyChef.data.datasources.remote.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyChefApi {
    @GET("filter.php")
    suspend fun getRecipesByCategory(@Query("c") category: String): RecipeResponse

    @GET("lookup.php")
    suspend fun getRecipeDetails(@Query("i") id: String): RecipeResponse
}