package com.example.dailychef.core.di

import android.content.Context
import com.example.dailychef.core.network.DailyChefApi
import com.example.dailychef.features.DailyChef.data.repositories.DailyChefRepositoryImpl
import com.example.dailychef.features.DailyChef.domain.repositories.DailyChefRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    // Configuración base de Retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Servicio de API único (Singleton)
    val dailyChefApi: DailyChefApi by lazy {
        retrofit.create(DailyChefApi::class.java)
    }

    // Repositorio que usaremos en toda la app
    val dailyChefRepository: DailyChefRepository by lazy {
        DailyChefRepositoryImpl(dailyChefApi)
    }
}