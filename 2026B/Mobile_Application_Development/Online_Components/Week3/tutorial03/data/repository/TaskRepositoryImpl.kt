package com.example.tutorial03.data.repository

import com.example.tutorial03.data.datasource.FakeTaskDataSource
import com.example.tutorial03.domain.model.Task
import com.example.tutorial03.domain.repository.TaskRepository

class TaskRepositoryImpl : TaskRepository {
    override fun getTasks(): List<Task> = FakeTaskDataSource.tasks
    override fun addTask(task: Task) { FakeTaskDataSource.tasks.add(task) }
    override fun deleteTask(taskId: String) {
        FakeTaskDataSource.tasks.removeIf { it.id.equals(taskId) }
    }
    override fun getTaskById(taskId: String): Task? =
        FakeTaskDataSource.tasks.find { it.id.equals(taskId) }
}
