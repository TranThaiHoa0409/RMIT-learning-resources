package com.example.lecture11

class LoginUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return userRepository.login(email, password)
    }
}