package com.example.dailychef.features.DailyChef.domain.repositories

import com.example.dailychef.features.DailyChef.domain.entities.Recipe

interface DailyChefRepository {
    suspend fun getRecipesByCategory(category: String): List<Recipe>

    suspend fun getRecipeById(id: String): Recipe?
}