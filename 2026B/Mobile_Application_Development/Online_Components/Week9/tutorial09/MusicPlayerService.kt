package com.example.tutorial09


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.io.File

class MusicPlayerService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var currentPath: String? = null
    private var lastPosition: Int = 0
    private var playlist: List<String> = emptyList()
    private var currentIndex: Int = -1

    inner class LocalBinder : Binder() {
        fun getService(): MusicPlayerService = this@MusicPlayerService
    }

    private val binder = LocalBinder()
    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createChannel()
        when (intent?.action) {
            ACTION_PLAY -> {
                val path = intent.getStringExtra("PATH")
                val list = intent.getStringArrayListExtra("PLAYLIST")
                if (list != null) playlist = list

                if (path != null && path != currentPath) {
                    // new song
                    currentIndex = playlist.indexOf(path).takeIf { it >= 0 } ?: -1
                    playSong(path)
                } else {
                    // resume current song
                    resumeSong()
                }
            }
            ACTION_PAUSE -> pauseSong()
            ACTION_STOP -> {
                stopSong()
                return START_NOT_STICKY
            }
            ACTION_NEXT -> nextSong()
            ACTION_PREV -> prevSong()
        }
        updateNotification()
        return START_NOT_STICKY
    }

    fun playSong(path: String? = null) {
        if (path != null) currentPath = path
        stopSongInternal()
        if (currentPath == null) return

        mediaPlayer = MediaPlayer().apply {
            setDataSource(currentPath)
            setOnPreparedListener {
                start()
                this@MusicPlayerService.isPlaying = true
                lastPosition = 0
                updateNotification()
            }
            setOnCompletionListener {
                nextSong() // auto-next when finished
            }
            prepareAsync()
        }
    }

    fun resumeSong() {
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.seekTo(lastPosition)
                it.start()
                isPlaying = true
                updateNotification()
            }
        }
    }

    fun pauseSong() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                lastPosition = it.currentPosition
                it.pause()
                isPlaying = false
                updateNotification()
            }
        }
    }

    fun stopSong() {
        stopSongInternal()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(NOTIF_ID)
    }

    private fun stopSongInternal() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
        lastPosition = 0
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    private fun nextSong() {
        if (playlist.isNotEmpty() && currentIndex >= 0) {
            currentIndex = (currentIndex + 1) % playlist.size
            playSong(playlist[currentIndex])
        }
    }

    private fun prevSong() {
        if (playlist.isNotEmpty() && currentIndex >= 0) {
            currentIndex = if (currentIndex - 1 < 0) playlist.size - 1 else currentIndex - 1
            playSong(playlist[currentIndex])
        }
    }

    private fun updateNotification() {
        val playIntent = Intent(this, MusicPlayerService::class.java).apply { action = ACTION_PLAY }
        val pauseIntent = Intent(this, MusicPlayerService::class.java).apply { action = ACTION_PAUSE }
        val stopIntent = Intent(this, MusicPlayerService::class.java).apply { action = ACTION_STOP }
        val nextIntent = Intent(this, MusicPlayerService::class.java).apply { action = ACTION_NEXT }
        val prevIntent = Intent(this, MusicPlayerService::class.java).apply { action = ACTION_PREV }

        val pendingPlay = PendingIntent.getService(this, 0, playIntent, PendingIntent.FLAG_IMMUTABLE)
        val pendingPause = PendingIntent.getService(this, 1, pauseIntent, PendingIntent.FLAG_IMMUTABLE)
        val pendingStop = PendingIntent.getService(this, 2, stopIntent, PendingIntent.FLAG_IMMUTABLE)
        val pendingNext = PendingIntent.getService(this, 3, nextIntent, PendingIntent.FLAG_IMMUTABLE)
        val pendingPrev = PendingIntent.getService(this, 4, prevIntent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText(
                if (isPlaying) "Playing: ${File(currentPath ?: "").name}"
                else "Paused: ${File(currentPath ?: "").name}"
            )
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setOngoing(isPlaying)
            .addAction(android.R.drawable.ic_media_previous, "Prev", pendingPrev)

        if (isPlaying) {
            builder.addAction(android.R.drawable.ic_media_pause, "Pause", pendingPause)
        } else {
            builder.addAction(android.R.drawable.ic_media_play, "Play", pendingPlay)
        }

        builder.addAction(android.R.drawable.ic_media_next, "Next", pendingNext)
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop", pendingStop)

        startForeground(NOTIF_ID, builder.build())
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Music Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val ACTION_PLAY = "ACTION_PLAY"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_STOP = "ACTION_STOP"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_PREV = "ACTION_PREV"
        private const val CHANNEL_ID = "music_channel"
        private const val NOTIF_ID = 1
    }
}