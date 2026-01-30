package com.example.dailychef.core.network

import com.example.dailychef.features.DailyChef.data.datasources.remote.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyChefApi {
    // Para obtener la lista de platos por categoría (ej. Seafood, Chicken)
    @GET("filter.php")
    suspend fun getRecipesByCategory(@Query("c") category: String): RecipeResponse

    // Para obtener el detalle de una receta específica por ID
    @GET("lookup.php")
    suspend fun getRecipeDetails(@Query("i") id: String): RecipeResponse
}