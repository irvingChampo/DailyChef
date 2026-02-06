package com.example.dailychef.features.DailyChef.presentation.screens

import com.example.dailychef.features.DailyChef.domain.entities.Recipe

data class DailyChefUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val favoriteIds: Set<String> = emptySet(),
    val showFavoritesOnly: Boolean = false,
    val selectedRecipe: Recipe? = null,
    val error: String? = null,
    val currentCategory: String = "Seafood"
) {
    val filteredRecipes: List<Recipe>
        get() = if (showFavoritesOnly) {
            recipes.filter { favoriteIds.contains(it.id) }
        } else {
            recipes
        }
}