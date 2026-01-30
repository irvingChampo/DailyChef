package com.example.dailychef.features.DailyChef.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.dailychef.features.DailyChef.presentation.components.RecipeCard
import com.example.dailychef.features.DailyChef.presentation.viewmodels.DailyChefViewModel
import com.example.dailychef.features.DailyChef.presentation.viewmodels.DailyChefViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyChefScreen(factory: DailyChefViewModelFactory) {
    val viewModel: DailyChefViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Daily Chef", fontWeight = FontWeight.Black) },
                navigationIcon = {
                    if (uiState.selectedRecipe != null) {
                        IconButton(onClick = { viewModel.clearSelection() }) {
                            Icon(Icons.Default.ArrowBack, "Volver")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                uiState.error != null -> {
                    Text(uiState.error!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
                }
                uiState.selectedRecipe != null -> {
                    // MODO DETALLE
                    RecipeDetailContent(recipe = uiState.selectedRecipe!!)
                }
                else -> {
                    // MODO LISTA (Selección de categoría y rejilla)
                    Column {
                        CategorySelector(
                            current = uiState.currentCategory,
                            onSelect = { viewModel.loadRecipes(it) }
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(uiState.recipes) { recipe ->
                                RecipeCard(
                                    name = recipe.name,
                                    imageUrl = recipe.imageUrl,
                                    onClick = { viewModel.selectRecipe(recipe.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategorySelector(current: String, onSelect: (String) -> Unit) {
    val categories = listOf("Seafood", "Chicken", "Beef", "Vegetarian")
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        categories.forEach { cat ->
            FilterChip(
                selected = current == cat,
                onClick = { onSelect(cat) },
                label = { Text(cat) }
            )
        }
    }
}

@Composable
fun RecipeDetailContent(recipe: com.example.dailychef.features.DailyChef.domain.entities.Recipe) {
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