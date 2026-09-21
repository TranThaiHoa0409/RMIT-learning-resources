package com.example.tutorial05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tutorial05.presentation.ui.InteractiveMapScreen
import com.example.tutorial05.presentation.ui.components.RequestLocationPermission
import com.example.tutorial05.presentation.ui.theme.Tutorial05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tutorial05Theme {
                RequestLocationPermission { InteractiveMapScreen() }
            }
        }
    }
}
