package com.example.tutorial03.domain.usecase

import com.example.tutorial03.domain.model.Task
import com.example.tutorial03.domain.repository.TaskRepository

class GetTasksUseCase(private val repo: TaskRepository) {
    operator fun invoke(): List<Task> = repo.getTasks()
}