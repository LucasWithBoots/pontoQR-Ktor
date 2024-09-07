package com.example.plugins

import com.example.repositories.user.PostgresUserRepository
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization(
    userRepository: PostgresUserRepository
) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/users") {
                val users = userRepository.allUsers()
            call.respond(users)
            }
    }
}
