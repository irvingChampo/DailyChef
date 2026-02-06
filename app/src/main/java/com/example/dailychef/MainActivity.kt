package com.example.dailychef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.dailychef.core.di.AppContainer
import com.example.dailychef.core.navigation.DailyChefNavGraph
import com.example.dailychef.core.ui.theme.AppTheme
import com.example.dailychef.features.DailyChef.di.DailyChefModule

class MainActivity : ComponentActivity() {

    lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(this)
        val dailyChefModule = DailyChefModule(appContainer)

        enableEdgeToEdge()

        setContent {
            AppTheme {
                val navController = rememberNavController()
                DailyChefNavGraph(
                    navController = navController,
                    factory = dailyChefModule.provideDailyChefViewModelFactory()
                )
            }
        }
    }
}