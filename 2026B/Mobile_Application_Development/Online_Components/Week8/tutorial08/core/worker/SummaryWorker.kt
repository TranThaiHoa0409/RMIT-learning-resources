package com.example.tutorial08.core.worker

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.tutorial08.core.notification.Notify
import com.example.tutorial08.data.repository.EventLogRepository

/**
 * Periodic background job that summarizes recent activity and posts a notification.
 * Demonstrates deferred work that runs even if the app is in the background.
 */
class SummaryWorker(appContext: Context, params: WorkerParameters) : Worker(appContext, params) {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result {
        val repo = EventLogRepository(applicationContext)
        val hourAgo = System.currentTimeMillis() - 60 * 60 * 1000
        val count = repo.recentCount(hourAgo)
        if (count > 0) {
            Notify.show(applicationContext, "Event summary", "$count events in the last hour")
        }
        return Result.success()
    }
}