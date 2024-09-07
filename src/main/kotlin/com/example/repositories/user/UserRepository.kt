package com.example.repositories.user

import com.example.model.User

interface UserRepository {
    suspend fun allUsers(): List<User>
    suspend fun userById(id: Int): User?
}