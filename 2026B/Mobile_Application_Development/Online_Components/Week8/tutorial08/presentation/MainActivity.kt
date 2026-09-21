package com.example.tutorial08.presentation

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.tutorial08.core.notification.Notify
import com.example.tutorial08.core.receiver.CustomEventReceiver
import com.example.tutorial08.core.receiver.CustomEventReceiver.Companion.ACTION_HAPPY
import com.example.tutorial08.core.receiver.CustomEventReceiver.Companion.ACTION_MISS
import com.example.tutorial08.core.worker.SummaryWorker
import com.example.tutorial08.data.repository.EventLogRepository
import com.example.tutorial08.domain.model.Event
import java.time.Instant
import java.time.ZoneId
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    private val customReceiver = CustomEventReceiver()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 1) Ensure notification channel exists
        Notify.ensureChannel(this)


        // 2) Request notification permission on Android 13+
        if (Build.VERSION.SDK_INT >= 33) {
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (!granted) Toast.makeText(this, "Notifications disabled", Toast.LENGTH_SHORT).show()
            }.launch(Manifest.permission.POST_NOTIFICATIONS)
        }


        // 3) Register runtime receiver for custom broadcasts
        val filter = IntentFilter().apply {
            addAction(ACTION_HAPPY)
            addAction(ACTION_MISS)
        }
        ContextCompat.registerReceiver(this, customReceiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED)


        // 4) Schedule periodic background work (unique task)
        val req = PeriodicWorkRequestBuilder<SummaryWorker>(15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder()
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "summary",
            ExistingPeriodicWorkPolicy.UPDATE,
            req
        )


        setContent { AppScreen() }
    }


    override fun onDestroy() {
        super.onDestroy()
        // Always unregister dynamic receivers to avoid leaks
        unregisterReceiver(customReceiver)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    val context = LocalContext.current
    val repo = remember { EventLogRepository(context) }
    var events by remember { mutableStateOf(repo.readAll()) }


    fun refresh() { events = repo.readAll() }


    Scaffold(topBar = { TopAppBar(title = { Text("Emotion Tracking - Week 8") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = {
                // Send a custom broadcast indicating a happy status
                    context.sendBroadcast(Intent(ACTION_HAPPY).setPackage(context.packageName))
                }) { Text("I'm Happy") }


                Button(onClick = {
                // Send a custom broadcast indicating a sad status
                    context.sendBroadcast(Intent(ACTION_MISS).setPackage(context.packageName))
                }) { Text("I'm Sad") }


                Button(onClick = { refresh() }) { Text("Refresh") }
            }


            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
            Text("Event Log", style = MaterialTheme.typography.titleMedium)


            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(events) { e -> EventRow(e) }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventRow(e: Event) {
    val dt = Instant.ofEpochMilli(e.timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime()
    ListItem(
        headlineContent = { Text("${e.type} — ${e.source}") },
        supportingContent = { Text("${dt}: ${e.message}") }
    )
    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
}