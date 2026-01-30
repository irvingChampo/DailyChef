package com.example.dailychef.features.DailyChef.data.datasources.remote.mapper

import com.example.dailychef.features.DailyChef.data.datasources.remote.model.RecipeDto
import com.example.dailychef.features.DailyChef.domain.entities.Recipe

fun RecipeDto.toDomain(): Recipe {
    // Juntamos los ingredientes en una lista limpia
    val ingredientsList = listOfNotNull(
        strIngredient1, strIngredient2, strIngredient3,
        strIngredient4, strIngredient5, strIngredient6
    ).filter { it.isNotBlank() }

    return Recipe(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        category = this.category ?: "",
        instructions = this.instructions ?: "",
        ingredients = ingredientsList
    )
}