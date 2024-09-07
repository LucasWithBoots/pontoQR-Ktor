package com.example.repositories.user

import com.example.mapping.UserDAO
import com.example.mapping.Users
import com.example.mapping.daoToModel
import com.example.model.User
import com.example.plugins.suspendTransaction

class PostgresUserRepository : UserRepository {
    override suspend fun allUsers(): List<User> = suspendTransaction {
        UserDAO.all().map(::daoToModel)
    }

    override suspend fun userById(id: Int): User? = suspendTransaction {
        UserDAO.find { (Users.id eq id) }.limit(1).map(::daoToModel).firstOrNull()
    }
}