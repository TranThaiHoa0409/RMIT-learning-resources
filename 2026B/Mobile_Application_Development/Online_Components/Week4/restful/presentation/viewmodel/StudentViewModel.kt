package com.example.restful.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restful.data.model.NewStudent
import com.example.restful.data.model.Student
import com.example.restful.data.repository.ApiClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentViewModel : ViewModel() {
    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message.asSharedFlow()

    fun getStudents() {
        viewModelScope.launch {
            try {
                _students.value = ApiClient.studentApi.getStudents()
            } catch (e: Exception) {
                _message.emit(e.message?:"Fail to load")
            }
        }
    }

    fun addStudent(name: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                ApiClient.studentApi.addStudent(NewStudent(name = name))
                getStudents()
                _message.emit("Student added successfully")
                onComplete()
            } catch (e: Exception) {
                _message.emit("Failed to add student")
            }
        }
    }


    fun deleteStudent(id: Int) {
        viewModelScope.launch {
            try {
                Log.d("StudentViewModel", "Deleting student with id: $id")
                val response = ApiClient.studentApi.deleteStudent(id)
                if (response.isSuccessful) {
                    getStudents()
                    _message.emit("Student deleted (code: ${response.code()})")
                } else {
                    _message.emit("Delete failed (code: ${response.code()})")
                }
            } catch (e: Exception) {
                _message.emit("Failed to delete student")
            }
        }
    }

    fun updateStudent(student: Student, onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.studentApi.updateStudent(student.id, student)
                if (response.isSuccessful) {
                    getStudents()
                    _message.emit("Student updated (code: ${response.code()})")
                    onComplete()
                } else {
                    _message.emit("Update failed (code: ${response.code()})")
                }
            } catch (e: Exception) {
                _message.emit("Failed to update student")
            }
        }
    }
}