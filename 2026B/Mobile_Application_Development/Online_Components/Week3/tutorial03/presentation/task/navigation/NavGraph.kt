package com.example.tutorial03.presentation.task

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tutorial03.presentation.task.navigation.TaskNavRoute

@Composable
fun TaskNavGraph(
    navController: NavHostController,
    viewModel: TaskViewModel
) {
    NavHost(
        navController = navController,
        startDestination = TaskNavRoute.Home.route
    ) {
        composable(route = TaskNavRoute.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate(TaskNavRoute.Add.route)
                },
                onTaskClick = { taskId ->
                    navController.navigate(TaskNavRoute.Detail.createRoute(taskId))
                }
            )
        }

        composable(route = TaskNavRoute.Add.route) {
            AddTaskScreen(
                viewModel = viewModel,
                onSaveDone = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = TaskNavRoute.Detail.route) {
            backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId").orEmpty()
            TaskDetailScreen(
                viewModel = viewModel,
                taskId = taskId,
                onDeleteDone = {
                    navController.popBackStack()
                }
            )
        }
    }
}
