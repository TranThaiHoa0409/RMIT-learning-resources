package com.example.tutorial03.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tutorial03.domain.model.Task

@Composable
fun AddTaskScreen(viewModel: TaskViewModel, onSaveDone: () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }

    var title by rememberSaveable { mutableStateOf("") }
    var dueDate by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    val isValid = title.isNotBlank() && dueDate.isNotBlank()

    // We need this because onClick does not accept Composable - LaunchedEffect in our case
    var isClicked by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
            OutlinedTextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text("Due Date") })
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    viewModel.addTask(Task(title = title, dueDate = dueDate, description = description))
                    isClicked = true // trigger snackbar + navigation
                },
                enabled = isValid
            ) {
                Text("Save")
            }
        }
    }

    //  Launch snackbar after click, then navigate. You might have to wait a bit
    //  as snackbar is a suspend function (~4 seconds)
    LaunchedEffect(isClicked) {
        if (isClicked) {
            snackbarHostState.showSnackbar("Task '$title' added.")
            onSaveDone()
            isClicked = false
        }
    }
}

