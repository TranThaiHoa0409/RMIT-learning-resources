package com.example.tutorial08.domain.model

data class Event(
    val timestamp: Long,
    val source: String,
    val type: String,
    val message: String
)