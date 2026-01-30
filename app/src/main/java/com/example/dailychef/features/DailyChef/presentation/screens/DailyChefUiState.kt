package com.example.dailychef.features.DailyChef.presentation.screens // Paquete actualizado

import com.example.dailychef.features.DailyChef.domain.entities.Recipe

data class DailyChefUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val selectedRecipe: Recipe? = null,
    val error: String? = null,
    val currentCategory: String = "Seafood"
)