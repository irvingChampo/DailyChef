package com.example.dailychef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dailychef.core.di.AppContainer
import com.example.dailychef.features.DailyChef.di.DailyChefModule
import com.example.dailychef.features.DailyChef.presentation.screens.DailyChefScreen // Saldrá en rojo hasta el Paso 6
import com.example.dailychef.core.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    // Contenedor global
    lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inicializamos dependencias
        appContainer = AppContainer(this)
        val dailyChefModule = DailyChefModule(appContainer)

        enableEdgeToEdge()

        setContent {
            AppTheme {
                // 2. Llamamos a la pantalla principal pasando el Factory
                DailyChefScreen(dailyChefModule.provideDailyChefViewModelFactory())
            }
        }
    }
}