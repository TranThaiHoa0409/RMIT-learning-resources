package com.example.lecture06

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

    val tasks = mutableStateListOf<Task>()

    init {
        refreshTasks()
    }

    fun refreshTasks() {
        viewModelScope.launch {
            val allTasks = taskDao.getAllTasks()
            tasks.clear()
            tasks.addAll(allTasks)
        }
    }

    fun addTask(title: String, completed: Boolean = false) {
        viewModelScope.launch {
            taskDao.insertTask(Task(title = title, completed = completed))
            refreshTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
            refreshTasks()
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            taskDao.deleteTaskById(taskId)
            refreshTasks()
        }
    }
}
