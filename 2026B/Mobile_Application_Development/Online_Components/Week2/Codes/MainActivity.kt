package com.example.exercise

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainScreen() }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }

    val formLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        result ->
        val message = result.data?.getStringExtra("result_message")
        Toast.makeText(context, message ?: "", Toast.LENGTH_SHORT).show()
    }

    val serviceLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        result ->
        val message = result.data?.getStringExtra("result_message")
        Toast.makeText(context, message ?: "", Toast.LENGTH_SHORT).show()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(context, StudentFormActivity::class.java)
            intent.putExtra("student_name", name)
            formLauncher.launch(intent)
        }) {
            Text("Student Form")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val intent = Intent(context, StudentServicesActivity::class.java)
            serviceLauncher.launch(intent)
        }) {
            Text("Student Services")
        }
    }
}
