package com.example.dailychef.features.DailyChef.di

import com.example.dailychef.core.di.AppContainer
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipeDetailsUseCase
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipesByCategoryUseCase
import com.example.dailychef.features.DailyChef.presentation.viewmodels.DailyChefViewModelFactory

class DailyChefModule(
    private val appContainer: AppContainer
) {
    // Proveemos los casos de uso (internos)
    private fun provideGetRecipesUseCase(): GetRecipesByCategoryUseCase {
        return GetRecipesByCategoryUseCase(appContainer.dailyChefRepository)
    }

    private fun provideGetDetailsUseCase(): GetRecipeDetailsUseCase {
        return GetRecipeDetailsUseCase(appContainer.dailyChefRepository)
    }

    // Proveemos el Factory para el ViewModel (público)
    fun provideDailyChefViewModelFactory(): DailyChefViewModelFactory {
        return DailyChefViewModelFactory(
            getRecipesUseCase = provideGetRecipesUseCase(),
            getDetailsUseCase = provideGetDetailsUseCase()
        )
    }
}