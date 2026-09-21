package com.example.exercise

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class StudentFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentName = intent.getStringExtra("student_name") ?: ""
        setContent { StudentFormScreen(studentName) }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun StudentFormScreen(name: String) {
    val context = LocalContext.current as Activity
    var formName by remember { mutableStateOf(name) }

    val serviceLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val message = result.data?.getStringExtra("result_message")
        val returnIntent = Intent().apply {
            putExtra("result_message", message)
        }
        context.setResult(Activity.RESULT_OK, returnIntent)
        context.finish()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Student Form", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = formName,
            onValueChange = { formName = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val returnIntent = Intent().apply {
                putExtra("result_message", "Thank you $formName for submitting your form.")
            }
            context.setResult(Activity.RESULT_OK, returnIntent)
            context.finish()
        }) {
            Text("Submit")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val intent = Intent(context, StudentServicesActivity::class.java)
            serviceLauncher.launch(intent)
        }) {
            Text("Go to Services")
        }
    }
}

