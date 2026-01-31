package com.example.dailychef.features.DailyChef.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.dailychef.features.DailyChef.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : FavoritesRepository {

    private companion object {
        // Clave única para guardar el conjunto de IDs
        val FAVORITES_KEY = stringSetPreferencesKey("favorite_recipes")
    }

    override fun getFavoriteIds(): Flow<Set<String>> {
        return dataStore.data.map { preferences ->
            preferences[FAVORITES_KEY] ?: emptySet()
        }
    }

    override suspend fun toggleFavorite(recipeId: String) {
        // Editamos el DataStore de forma asíncrona
        dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITES_KEY]?.toMutableSet() ?: mutableSetOf()

            if (currentFavorites.contains(recipeId)) {
                currentFavorites.remove(recipeId) // Ya era favorito, lo quitamos
            } else {
                currentFavorites.add(recipeId)    // No era favorito, lo agregamos
            }

            preferences[FAVORITES_KEY] = currentFavorites
        }
    }
}