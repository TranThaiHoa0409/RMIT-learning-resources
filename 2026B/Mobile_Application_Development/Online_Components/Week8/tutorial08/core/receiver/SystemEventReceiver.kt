package com.example.tutorial08.core.receiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import com.example.tutorial08.core.notification.Notify
import com.example.tutorial08.data.repository.EventLogRepository
import com.example.tutorial08.domain.model.Event

/**
 * Manifest-declared receiver for a whitelisted implicit broadcast: BATTERY_LOW.
 * This can wake the app even when not running.
 */
class SystemEventReceiver : BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BATTERY_LOW.equals(intent.action)) {
            val repo = EventLogRepository(context)
            repo.append(
                Event(
                    System.currentTimeMillis(),
                    "system",
                    "BATTERY_LOW",
                    "Battery is low"
                )
            )
            // Immediately inform the user with a notification
            Notify.show(context, title = "Battery Low", text = "Logged system event")
        }
    }
}