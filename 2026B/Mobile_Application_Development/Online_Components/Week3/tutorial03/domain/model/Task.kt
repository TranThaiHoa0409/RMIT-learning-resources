package com.example.tutorial03.domain.model

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val dueDate: String,
    val description: String? = null
)