package com.example.tutorial03.presentation.task.navigation

sealed class TaskNavRoute(val route: String) {
    object Home : TaskNavRoute("home")
    object Add : TaskNavRoute("add")
    object Detail : TaskNavRoute("detail/{taskId}") {
        fun createRoute(taskId: String) = "detail/$taskId"
    }
}