package com.example.dailychef.features.DailyChef.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipeDetailsUseCase
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipesByCategoryUseCase
import com.example.dailychef.features.DailyChef.domain.usecase.GetFavoriteIdsUseCase
import com.example.dailychef.features.DailyChef.domain.usecase.ToggleFavoriteUseCase

class DailyChefViewModelFactory(
    private val getRecipesUseCase: GetRecipesByCategoryUseCase,
    private val getDetailsUseCase: GetRecipeDetailsUseCase,
    private val getFavoriteIdsUseCase: GetFavoriteIdsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyChefViewModel::class.java)) {
            return DailyChefViewModel(
                getRecipesUseCase,
                getDetailsUseCase,
                getFavoriteIdsUseCase,
                toggleFavoriteUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}