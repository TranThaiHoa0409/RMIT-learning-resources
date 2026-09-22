package com.example.tutorial09

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.io.File

class MainActivity : ComponentActivity() {

    private var boundService: MusicPlayerService? by mutableStateOf(null)
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicPlayerService.LocalBinder
            boundService = binder.getService()
            isBound = true
            Log.d("MainActivity", "Service Bound")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            boundService = null
            isBound = false
            Log.d("MainActivity", "Service Unbound")
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MusicPlayerService::class.java).also {
            ContextCompat.startForegroundService(this, it) // start service
            bindService(it, connection, BIND_AUTO_CREATE)  // bind for direct control
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) unbindService(connection)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {

                RequestNotificationPermission()

                Surface(Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        PlayerScreen()

                        HorizontalDivider(
                            Modifier.padding(vertical = 16.dp),
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
                        )

                        SongListScreen()
                    }
                }
            }
        }
    }
}

fun enqueueDownload(context: Context, url: String) {
    val data = Data.Builder()
        .putString("URL", url)
        .build()

    val request = OneTimeWorkRequestBuilder<DownloadWorker>()
        .setInputData(data)
        .addTag("song_download")
        .build()

    WorkManager.getInstance(context).enqueue(request)
}


@Composable
fun SongListScreen(context: Context = LocalContext.current) {
    val songs = listOf(
        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"
    )

    Column {
        Text("Songs (Notification Controlled)", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        songs.forEach { url ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(url.substringAfterLast('/'))
                Button(onClick = {
                    enqueueDownload(context, url)
                }) {
                    Text("Download")
                }
                Button(onClick = {
                    val intent = Intent(context, MusicPlayerService::class.java).apply {
                        action = MusicPlayerService.ACTION_PLAY
                        putExtra("PATH", url) // ✅ matches service
                    }
                    ContextCompat.startForegroundService(context, intent)
                }) {
                    Text("Play")
                }
            }
        }
    }
}

fun getLocalSongs(context: Context): List<File> {
    val musicDir = File(context.filesDir, "music")
    if (!musicDir.exists()) musicDir.mkdirs()
    return musicDir.listFiles()?.toList() ?: emptyList()
}

@Composable
fun PlayerScreen(context: Context = LocalContext.current) {
    val workManager = WorkManager.getInstance(context)

    var localSongs by remember { mutableStateOf(getLocalSongs(context)) }

    // Observe WorkManager for completed downloads
    val workInfos by workManager.getWorkInfosByTagLiveData("song_download")
        .observeAsState(initial = emptyList())

    LaunchedEffect(workInfos) {
        // Refresh list whenever a download completes
        if (workInfos.any { it.state.isFinished }) {
            localSongs = getLocalSongs(context)
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Available Songs", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        if (localSongs.isEmpty()) {
            Text("No songs downloaded yet.", style = MaterialTheme.typography.bodyMedium)
        } else {
            localSongs.forEach { file ->
                Row(
                    Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(file.name)
                    Button(onClick = {
                        val playlist = localSongs.map { it.absolutePath }
                        val intent = Intent(context, MusicPlayerService::class.java).apply {
                            action = MusicPlayerService.ACTION_PLAY
                            putExtra("PATH", file.absolutePath)
                            putStringArrayListExtra("PLAYLIST", ArrayList(playlist))
                        }
                        ContextCompat.startForegroundService(context, intent)
                    }) {
                        Text("Play")
                    }
                }
            }
        }
    }
}


@Composable
fun RequestNotificationPermission() {
    val context = LocalContext.current

    // Check current state
    val alreadyGranted = remember {
        Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    var granted by remember { mutableStateOf(alreadyGranted) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        granted = isGranted
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= 33 && !granted) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}