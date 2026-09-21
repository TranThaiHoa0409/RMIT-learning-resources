package com.example.tutorial03.presentation.task

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tutorial03.data.repository.TaskRepositoryImpl
import com.example.tutorial03.domain.model.Task
import com.example.tutorial03.domain.usecase.AddTaskUseCase
import com.example.tutorial03.domain.usecase.DeleteTaskUseCase
import com.example.tutorial03.domain.usecase.GetTasksUseCase

class TaskViewModel : ViewModel() {
    private val repository = TaskRepositoryImpl()

    // UseCases
    private val addTaskUseCase = AddTaskUseCase(repository)
    private val getTasksUseCase = GetTasksUseCase(repository)
    private val deleteTaskUseCase = DeleteTaskUseCase(repository)

    // Reactive UI state
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> get() = _tasks

    val taskCount by derivedStateOf { _tasks.size }

    init {
        _tasks.addAll(getTasksUseCase())
    }

    fun addTask(task: Task) {
        addTaskUseCase(task) // update Database
        _tasks.add(task) // update UI
    }

    fun deleteTask(taskId: String) {
        deleteTaskUseCase(taskId)
        _tasks.removeIf { it.id == taskId }
    }

    fun getTaskById(taskId: String): Task? {
        return _tasks.find { it.id == taskId }
    }
}
