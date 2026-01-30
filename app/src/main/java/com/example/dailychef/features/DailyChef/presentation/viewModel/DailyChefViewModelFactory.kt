package com.example.dailychef.features.DailyChef.presentation.viewmodels // Paquete actualizado

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipeDetailsUseCase
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipesByCategoryUseCase

class DailyChefViewModelFactory(
    private val getRecipesUseCase: GetRecipesByCategoryUseCase,
    private val getDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyChefViewModel::class.java)) {
            return DailyChefViewModel(getRecipesUseCase, getDetailsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}