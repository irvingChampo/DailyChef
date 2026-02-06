package com.example.dailychef.features.DailyChef.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dailychef.features.DailyChef.presentation.components.RecipeCard
import com.example.dailychef.features.DailyChef.presentation.viewmodels.DailyChefViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListScreen(
    viewModel: DailyChefViewModel,
    onRecipeClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Daily Chef", fontWeight = FontWeight.Black) }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Selector de categorías
                val categories = listOf("Seafood", "Chicken", "Beef")
                categories.forEach { cat ->
                    FilterChip(
                        selected = uiState.currentCategory == cat && !uiState.showFavoritesOnly,
                        onClick = { viewModel.loadRecipes(cat) },
                        label = { Text(cat) }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                FilterChip(
                    selected = uiState.showFavoritesOnly,
                    onClick = { viewModel.toggleFilterFavorites() },
                    label = { Icon(Icons.Default.Favorite, null, modifier = Modifier.size(18.dp)) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color.Red.copy(alpha = 0.2f)
                    )
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(uiState.filteredRecipes) { recipe ->
                            RecipeCard(
                                name = recipe.name,
                                imageUrl = recipe.imageUrl,
                                isFavorite = uiState.favoriteIds.contains(recipe.id),
                                onFavoriteClick = { viewModel.onToggleFavorite(recipe.id) },
                                onClick = { onRecipeClick(recipe.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}