package com.example.dailychef.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.dailychef.core.network.DailyChefApi
import com.example.dailychef.features.DailyChef.data.repositories.DailyChefRepositoryImpl
import com.example.dailychef.features.DailyChef.data.repositories.FavoritesRepositoryImpl
import com.example.dailychef.features.DailyChef.domain.repositories.DailyChefRepository
import com.example.dailychef.features.DailyChef.domain.repositories.FavoritesRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Extensión para inicializar DataStore de forma segura
private val Context.dataStore by preferencesDataStore(name = "favorites_prefs")

class AppContainer(private val context: Context) {

    // --- RED (RETROFIT) ---
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val dailyChefApi: DailyChefApi by lazy {
        retrofit.create(DailyChefApi::class.java)
    }

    // --- REPOSITORIOS ---

    // Repositorio de la API
    val dailyChefRepository: DailyChefRepository by lazy {
        DailyChefRepositoryImpl(dailyChefApi)
    }

    // Repositorio de favoritos usando el dataStore del contexto
    val favoritesRepository: FavoritesRepository by lazy {
        FavoritesRepositoryImpl(context.dataStore)
    }
}