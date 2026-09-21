package com.example.restful.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restful.presentation.viewmodel.StudentViewModel


@Composable
fun EditStudentScreen(
    studentId: Int,
    viewModel: StudentViewModel = viewModel(),
    onStudentUpdated: () -> Unit
) {
    val students by viewModel.students.collectAsState()
    var name by remember { mutableStateOf("") }
    val student = students.find { it.id == studentId }

    LaunchedEffect(studentId) {
        if (student != null) name = student.name
    }

    Column(Modifier.padding(16.dp)) {
        Text("Edit Student")
        TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            if (student != null) {
                viewModel.updateStudent(student.copy(name = name)) {
                    onStudentUpdated()
                }
            }
        }) {
            Text("Update")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun editComposablePreview() {
    EditStudentScreen(0, viewModel(), onStudentUpdated = { /* No-op for preview */ } )
}
