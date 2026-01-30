package com.example.dailychef.features.DailyChef.domain.repositories

import com.example.dailychef.features.DailyChef.domain.entities.Recipe

interface DailyChefRepository {
    // Para obtener la lista de platos de una categoría
    suspend fun getRecipesByCategory(category: String): List<Recipe>

    // Para obtener el detalle de un plato por su ID
    suspend fun getRecipeById(id: String): Recipe?
}