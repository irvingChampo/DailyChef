package com.example.dailychef.features.DailyChef.domain.repositories

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    // Obtenemos un flujo de IDs de favoritos (ej: ["52772", "52811"])
    fun getFavoriteIds(): Flow<Set<String>>

    // Si el ID está, lo quita. Si no está, lo agrega.
    suspend fun toggleFavorite(recipeId: String)
}