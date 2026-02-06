package com.example.dailychef.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dailychef.features.DailyChef.presentation.screens.RecipeDetailScreen
import com.example.dailychef.features.DailyChef.presentation.screens.RecipeListScreen
import com.example.dailychef.features.DailyChef.presentation.viewmodels.DailyChefViewModelFactory

@Composable
fun DailyChefNavGraph(
    navController: NavHostController,
    factory: DailyChefViewModelFactory
) {
    NavHost(
        navController = navController,
        startDestination = Screen.RecipeList.route
    ) {
        composable(route = Screen.RecipeList.route) {
            RecipeListScreen(
                viewModel = viewModel(factory = factory),
                onRecipeClick = { id ->
                    navController.navigate(Screen.RecipeDetail.createRoute(id))
                }
            )
        }

        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
            RecipeDetailScreen(
                recipeId = recipeId,
                viewModel = viewModel(factory = factory),
                onBack = { navController.popBackStack() }
            )
        }
    }
}