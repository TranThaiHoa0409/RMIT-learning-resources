package com.example.tutorial08.data.repository

import android.content.Context
import com.example.tutorial08.domain.model.Event
import java.io.File

/**
 * Repository that writes/reads a simple CSV log in the app-specific external files dir.
 *
 * Using scoped storage means no READ/WRITE permission prompts are required for this directory.
 * In production, consider JSON or a database for reliability, escaping, and concurrency.
 */
class EventLogRepository(private val context: Context) {
    private val file: File by lazy { File(context.getExternalFilesDir(null), "event_log.csv") }


    fun append(event: Event) {
// Replace newlines to keep the CSV 1-line per entry (very basic escaping)
        val cleanMessage = event.message.replace('\n', ' ')
        val line = listOf(event.timestamp, event.source, event.type, cleanMessage)
            .joinToString(separator = ",")
        file.appendText(line + "\n")
    }



    fun readAll(): List<Event> =
        if (!file.exists()) emptyList() else file.readLines().mapNotNull { line ->
            val parts = line.split(",")
            if (parts.size < 4) null else Event(
                timestamp = parts[0].toLongOrNull() ?: 0L,
                source = parts[1],
                type = parts[2],
                message = parts.subList(3, parts.size).joinToString(",")
            )
        }.sortedByDescending { it.timestamp }


    fun recentCount(sinceMillis: Long): Int = readAll().count { it.timestamp >= sinceMillis }
}