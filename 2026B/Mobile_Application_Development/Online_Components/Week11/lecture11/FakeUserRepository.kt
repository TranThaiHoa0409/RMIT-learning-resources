package com.example.lecture11



// Fake repository for testing
class FakeUserRepository : UserRepository {
    private val validEmail = "user@email.com"
    private val validPassword = "1234"
    override suspend fun login(email: String, password: String): Boolean {
        return email.equals(validEmail) && password.equals(validPassword)
    }
}