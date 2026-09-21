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
 * Runtime-registered receiver for app-defined actions.
 * Prefer runtime receivers to save battery and respect modern background limits.
 */
class CustomEventReceiver : BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {
        val repo = EventLogRepository(context)
        when (intent.action) {
            ACTION_HAPPY -> repo.append(Event(now(), "custom", "HAPPY", "I am happy!"))
            ACTION_MISS -> repo.append(Event(now(), "custom", "SAD", "I am sad!"))
        }
    // Show a quick notification so users see feedback even if the app is backgrounded
        Notify.show(context, title = "New custom event", text = intent.action ?: "")
    }


    private fun now() = System.currentTimeMillis()


    companion object {
        const val ACTION_HAPPY = "HAPPY ACTION!!!"
        const val ACTION_MISS = "SAD ACTION!!!"
    }
}