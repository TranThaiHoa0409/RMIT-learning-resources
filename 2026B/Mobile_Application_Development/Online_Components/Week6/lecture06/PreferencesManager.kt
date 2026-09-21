package com.example.lecture06

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create DataStore instance (scoped to application context)
val Context.dataStore by preferencesDataStore("settings")

// Preference Keys
private val DARK_MODE = booleanPreferencesKey("dark_mode")

object PreferencesManager {

    // Save Dark Mode preference
    suspend fun saveDarkMode(context: Context, enabled: Boolean) {
        context.dataStore.edit { settings ->
            settings[DARK_MODE] = enabled
        }
    }

    // Observe Dark Mode preference as a Flow
    fun getDarkMode(context: Context): Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[DARK_MODE] ?: false
        }
}
