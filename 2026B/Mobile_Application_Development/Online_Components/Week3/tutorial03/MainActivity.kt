package com.example.tutorial03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tutorial03.presentation.task.AddTaskScreen
import com.example.tutorial03.presentation.task.HomeScreen
import com.example.tutorial03.presentation.task.TaskDetailScreen
import com.example.tutorial03.presentation.task.TaskNavGraph
import com.example.tutorial03.presentation.task.TaskViewModel
import com.example.tutorial03.ui.theme.Tutorial03Theme

class MainActivity : ComponentActivity() {

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tutorial03Theme {
                val navController = rememberNavController()
                TaskNavGraph(navController = navController, viewModel = taskViewModel)
            }
        }
    }
}

