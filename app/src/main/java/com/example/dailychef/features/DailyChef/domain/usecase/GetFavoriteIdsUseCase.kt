package com.example.dailychef.features.DailyChef.domain.usecase

import com.example.dailychef.features.DailyChef.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteIdsUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<Set<String>> {
        return repository.getFavoriteIds()
    }
}