package com.example.jetpackactivities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message = intent.getStringExtra("message_key") ?: "No message received"

        setContent {
            SecondScreen(
                message = message,
                onReturnResult = {
                    val resultIntent = Intent().apply {
                        putExtra("result_key", "Hi back from SecondActivity!")
                    }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }
            )
        }
    }
}

@Composable
fun SecondScreen(message: String, onReturnResult: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("SecondActivity", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Received message: $message")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onReturnResult) {
            Text("Return Result")
        }
    }
}
