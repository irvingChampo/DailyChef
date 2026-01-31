package com.example.dailychef.features.DailyChef.domain.usecase

import com.example.dailychef.features.DailyChef.domain.repositories.FavoritesRepository

class ToggleFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    // El operador invoke permite llamar a la clase como si fuera una función
    suspend operator fun invoke(recipeId: String) {
        repository.toggleFavorite(recipeId)
    }
}