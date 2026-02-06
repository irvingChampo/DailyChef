package com.example.dailychef.features.DailyChef.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipeDetailsUseCase
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipesByCategoryUseCase
import com.example.dailychef.features.DailyChef.domain.usecase.GetFavoriteIdsUseCase
import com.example.dailychef.features.DailyChef.domain.usecase.ToggleFavoriteUseCase
import com.example.dailychef.features.DailyChef.presentation.screens.DailyChefUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DailyChefViewModel(
    private val getRecipesUseCase: GetRecipesByCategoryUseCase,
    private val getDetailsUseCase: GetRecipeDetailsUseCase,
    private val getFavoriteIdsUseCase: GetFavoriteIdsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DailyChefUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeFavorites()
        loadRecipes(_uiState.value.currentCategory)
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            getFavoriteIdsUseCase().collect { ids ->
                _uiState.update { it.copy(favoriteIds = ids) }
            }
        }
    }

    fun loadRecipes(category: String) {
        _uiState.update { it.copy(isLoading = true, currentCategory = category, selectedRecipe = null) }
        viewModelScope.launch {
            val result = getRecipesUseCase(category)
            _uiState.update { state ->
                result.fold(
                    onSuccess = { list -> state.copy(isLoading = false, recipes = list, error = null) },
                    onFailure = { err -> state.copy(isLoading = false, error = err.message) }
                )
            }
        }
    }

    fun onToggleFavorite(recipeId: String) {
        viewModelScope.launch {
            toggleFavoriteUseCase(recipeId)
        }
    }

    fun toggleFilterFavorites() {
        _uiState.update { it.copy(showFavoritesOnly = !it.showFavoritesOnly) }
    }

    fun getRecipeById(recipeId: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = getDetailsUseCase(recipeId)
            _uiState.update { state ->
                result.fold(
                    onSuccess = { recipe -> state.copy(isLoading = false, selectedRecipe = recipe) },
                    onFailure = { err -> state.copy(isLoading = false, error = err.message) }
                )
            }
        }
    }

    fun clearSelection() {
        _uiState.update { it.copy(selectedRecipe = null) }
    }
}