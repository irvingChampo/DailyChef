package com.example.dailychef.features.DailyChef.presentation.screens

import com.example.dailychef.features.DailyChef.domain.entities.Recipe

data class DailyChefUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val favoriteIds: Set<String> = emptySet(), // IDs guardados en DataStore
    val showFavoritesOnly: Boolean = false,    // Estado del chip de filtro
    val selectedRecipe: Recipe? = null,
    val error: String? = null,
    val currentCategory: String = "Seafood"
) {
    // Esta lista calculada muestra las recetas filtradas si el switch está activo
    val filteredRecipes: List<Recipe>
        get() = if (showFavoritesOnly) {
            recipes.filter { favoriteIds.contains(it.id) }
        } else {
            recipes
        }
}