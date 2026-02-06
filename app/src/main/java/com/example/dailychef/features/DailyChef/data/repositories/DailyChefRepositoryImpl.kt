package com.example.dailychef.features.DailyChef.data.repositories

import com.example.dailychef.core.network.DailyChefApi
import com.example.dailychef.features.DailyChef.data.datasources.remote.mapper.toDomain
import com.example.dailychef.features.DailyChef.domain.entities.Recipe
import com.example.dailychef.features.DailyChef.domain.repositories.DailyChefRepository

class DailyChefRepositoryImpl(
    private val api: DailyChefApi
) : DailyChefRepository {

    override suspend fun getRecipesByCategory(category: String): List<Recipe> {
        val response = api.getRecipesByCategory(category)
        return response.meals?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun getRecipeById(id: String): Recipe? {
        val response = api.getRecipeDetails(id)
        return response.meals?.firstOrNull()?.toDomain()
    }
}