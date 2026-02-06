package com.example.dailychef.features.DailyChef.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    val meals: List<RecipeDto>?
)

data class RecipeDto(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val imageUrl: String,
    @SerializedName("strCategory") val category: String?,
    @SerializedName("strInstructions") val instructions: String?,
    val strIngredient1: String?, val strIngredient2: String?, val strIngredient3: String?,
    val strIngredient4: String?, val strIngredient5: String?, val strIngredient6: String?
)