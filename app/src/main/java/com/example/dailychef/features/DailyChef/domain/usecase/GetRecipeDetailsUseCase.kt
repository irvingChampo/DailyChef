package com.example.dailychef.features.DailyChef.domain.usecases

import com.example.dailychef.features.DailyChef.domain.entities.Recipe
import com.example.dailychef.features.DailyChef.domain.repositories.DailyChefRepository

class GetRecipeDetailsUseCase(
    private val repository: DailyChefRepository
) {
    suspend operator fun invoke(recipeId: String): Result<Recipe> {
        return try {
            val recipe = repository.getRecipeById(recipeId)
            if (recipe != null) {
                Result.success(recipe)
            } else {
                Result.failure(Exception("No se pudo cargar el detalle de la receta"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}