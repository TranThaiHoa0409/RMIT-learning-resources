package com.example.lecture06

import TaskListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //  Observe dark mode preference from DataStore
            val context = LocalContext.current
            val isDarkMode by PreferencesManager.getDarkMode(context)
                .collectAsState(initial = false)

            //  Wrap the entire app in a dynamic MaterialTheme
            MaterialTheme(
                colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()
            ) {
                TaskListScreen(context = this)
            }
        }
    }
}
