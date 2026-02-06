package com.example.dailychef.features.DailyChef.domain.usecase

import com.example.dailychef.features.DailyChef.domain.repositories.FavoritesRepository

class ToggleFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(recipeId: String) {
        repository.toggleFavorite(recipeId)
    }
}