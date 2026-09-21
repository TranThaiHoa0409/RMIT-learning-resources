package com.example.tutorial03.domain.usecase

import com.example.tutorial03.domain.model.Task
import com.example.tutorial03.domain.repository.TaskRepository

class AddTaskUseCase(private val repo: TaskRepository) {
    operator fun invoke(task: Task) = repo.addTask(task)
}