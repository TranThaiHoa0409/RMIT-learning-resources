package com.example.lecture06

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DarkModePreferenceScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Observe the preference from DataStore
    val isDarkMode by PreferencesManager.getDarkMode(context)
        .collectAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Dark Mode is ${if (isDarkMode) "ON" else "OFF"}")

        Spacer(modifier = Modifier.height(16.dp))

        Switch(
            checked = isDarkMode,
            onCheckedChange = { enabled ->
                coroutineScope.launch {
                    PreferencesManager.saveDarkMode(context, enabled)
                }
            }
        )
    }
}
