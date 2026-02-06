package com.example.dailychef.features.DailyChef.di

import com.example.dailychef.core.di.AppContainer
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipeDetailsUseCase
import com.example.dailychef.features.DailyChef.domain.usecases.GetRecipesByCategoryUseCase
import com.example.dailychef.features.DailyChef.domain.usecase.GetFavoriteIdsUseCase // Import nuevo
import com.example.dailychef.features.DailyChef.domain.usecase.ToggleFavoriteUseCase   // Import nuevo
import com.example.dailychef.features.DailyChef.presentation.viewmodels.DailyChefViewModelFactory

class DailyChefModule(
    private val appContainer: AppContainer
) {
    // --- Proveemos los casos de uso existentes ---
    private fun provideGetRecipesUseCase(): GetRecipesByCategoryUseCase {
        return GetRecipesByCategoryUseCase(appContainer.dailyChefRepository)
    }

    private fun provideGetDetailsUseCase(): GetRecipeDetailsUseCase {
        return GetRecipeDetailsUseCase(appContainer.dailyChefRepository)
    }

    private fun provideGetFavoriteIdsUseCase(): GetFavoriteIdsUseCase {
        return GetFavoriteIdsUseCase(appContainer.favoritesRepository)
    }

    private fun provideToggleFavoriteUseCase(): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(appContainer.favoritesRepository)
    }

    fun provideDailyChefViewModelFactory(): DailyChefViewModelFactory {
        return DailyChefViewModelFactory(
            getRecipesUseCase = provideGetRecipesUseCase(),
            getDetailsUseCase = provideGetDetailsUseCase(),
            getFavoriteIdsUseCase = provideGetFavoriteIdsUseCase(), // Nuevo
            toggleFavoriteUseCase = provideToggleFavoriteUseCase()  // Nuevo
        )
    }
}