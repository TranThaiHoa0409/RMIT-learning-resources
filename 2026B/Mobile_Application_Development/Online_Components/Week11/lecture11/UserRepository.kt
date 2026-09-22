package com.example.lecture11

interface UserRepository {
    suspend fun login(email: String, password: String): Boolean
}