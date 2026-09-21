package com.example.tutorial03.presentation.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskDetailScreen(viewModel: TaskViewModel, taskId: String, onDeleteDone: () -> Unit) {
    val task = viewModel.getTaskById(taskId)
    val snackbarHostState = remember { SnackbarHostState() }

    var isClicked by remember { mutableStateOf(false) }

    if (task == null) {
        Text("Task not found")
        return
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Column(Modifier.padding(16.dp)) {
            Text("Title: ${task.title}")
            Text("Due Date: ${task.dueDate}")
            Text("Description: ${task.description ?: "-"}")

            Spacer(Modifier.height(12.dp))
            Button(onClick = {
                isClicked = true
            }) {
                Text("Delete")
            }
        }

        //  Launch snackbar after click, then navigate. You might have to wait a bit
        //  as snackbar is a suspend function (~4 seconds)
        LaunchedEffect(isClicked) {
            if (isClicked) {
                snackbarHostState.showSnackbar("Task '${task.title}' deleted.")
                viewModel.deleteTask(task.id)
                onDeleteDone()
            }
        }
    }
}
