package com.example.lecture06

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    // Query all tasks
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    // Query a task by ID
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?

    // Insert a new task
    // Available conflict strategies: REPLACE, ABORT, FAIL, IGNORE, and ROLLBACK
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    // Update a task
    @Update
    suspend fun updateTask(task: Task)

    // Delete a task
    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Int)
}
