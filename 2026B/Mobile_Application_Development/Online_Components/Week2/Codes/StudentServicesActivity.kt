package com.example.exercise

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class StudentServicesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { StudentServicesScreen() }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun StudentServicesScreen() {
    val context = LocalContext.current as Activity
    val services = listOf("Library", "Counseling", "IT Support")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Student Services", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        services.forEach { service ->
            Button(
                onClick = {
                    val returnIntent = Intent().apply {
                        putExtra("result_message", "Thank you for selecting $service service.")
                    }
                    context.setResult(Activity.RESULT_OK, returnIntent)
                    context.finish() // Return to StudentFormActivity
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(service)
            }
        }

    }
}


