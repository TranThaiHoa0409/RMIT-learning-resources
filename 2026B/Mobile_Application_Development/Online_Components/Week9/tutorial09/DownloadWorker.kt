package com.example.tutorial09

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.net.URL

class DownloadWorker(
    ctx: Context,
    params: WorkerParameters
) : Worker(ctx, params) {

    override fun doWork(): Result {
        val url = inputData.getString("URL") ?: return Result.failure()
        val fileName = url.substringAfterLast("/")
        val musicDir = File(applicationContext.filesDir, "music")
        if (!musicDir.exists()) musicDir.mkdirs()

        val outFile = File(musicDir, fileName)

        return try {
            Log.d("DownloadWorker", "Starting download: $url")

            URL(url).openStream().use { input ->
                outFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            Log.d("DownloadWorker", "Download complete: ${outFile.absolutePath}")
            showNotification("Download complete", fileName)
            Result.success()
        } catch (e: Exception) {
            Log.e("DownloadWorker", "Download failed for $url", e)
            showNotification("Download failed", fileName)
            Result.failure()
        }
    }

    private fun showNotification(title: String, message: String) {
        val channelId = "download_channel"
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Download Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.stat_sys_download_done)
            .build()

        manager.notify((0..10000).random(), notification)
    }
}
