package com.example.dailychef.core.navigation

sealed class Screen(val route: String) {
    object RecipeList : Screen("recipe_list")
    object RecipeDetail : Screen("recipe_detail/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe_detail/$recipeId"
    }
}