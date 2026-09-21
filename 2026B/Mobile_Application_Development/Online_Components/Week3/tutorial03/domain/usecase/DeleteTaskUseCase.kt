package com.example.tutorial03.domain.usecase

import com.example.tutorial03.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repo: TaskRepository) {
    operator fun invoke(taskId: String) = repo.deleteTask(taskId)
}