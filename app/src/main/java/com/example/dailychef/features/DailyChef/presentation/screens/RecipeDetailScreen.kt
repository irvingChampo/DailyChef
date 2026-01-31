package com.example.dailychef.features.DailyChef.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.dailychef.features.DailyChef.presentation.viewmodels.DailyChefViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipeId: String,
    viewModel: DailyChefViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Cargar los detalles cuando entramos a la pantalla
    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Receta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Volver")
                    }
                },
                actions = {
                    uiState.selectedRecipe?.let { recipe ->
                        IconButton(onClick = { viewModel.onToggleFavorite(recipe.id) }) {
                            Icon(
                                imageVector = if (uiState.favoriteIds.contains(recipe.id))
                                    Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = if (uiState.favoriteIds.contains(recipe.id)) Color.Red else Color.Gray
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                uiState.selectedRecipe?.let { recipe ->
                    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                        AsyncImage(
                            model = recipe.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(250.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(recipe.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Ingredientes:", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                            recipe.ingredients.forEach { ingredient ->
                                Text("• $ingredient", style = MaterialTheme.typography.bodyLarge)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Instrucciones:", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                            Text(recipe.instructions, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}