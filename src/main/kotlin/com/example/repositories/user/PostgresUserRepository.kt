package com.example.repositories.user

import com.example.mapping.UserDAO
import com.example.mapping.daoToModel
import com.example.model.User
import com.example.plugins.suspendTransaction

class PostgresUserRepository: UserRepository {
    override suspend fun allUsers(): List<User> = suspendTransaction {
        UserDAO.all().map(::daoToModel)
    }
}