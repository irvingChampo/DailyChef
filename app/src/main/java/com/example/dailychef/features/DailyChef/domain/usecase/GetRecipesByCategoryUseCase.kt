package com.example.dailychef.features.DailyChef.domain.usecases

import com.example.dailychef.features.DailyChef.domain.entities.Recipe
import com.example.dailychef.features.DailyChef.domain.repositories.DailyChefRepository

class GetRecipesByCategoryUseCase(
    private val repository: DailyChefRepository
) {
    suspend operator fun invoke(category: String): Result<List<Recipe>> {
        return try {
            val recipes = repository.getRecipesByCategory(category)
            if (recipes.isEmpty()) {
                Result.failure(Exception("No se encontraron recetas para esta categoría"))
            } else {
                Result.success(recipes)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}