package com.example.plugins

import com.example.plugins.routing.userRoutes
import com.example.repositories.qrcode.PostgresQrCodeRepository
import com.example.repositories.user.PostgresUserRepository
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun Application.configureSerialization(
    userRepository: PostgresUserRepository,
    qrCodeRepository: PostgresQrCodeRepository
) {
    install(ContentNegotiation) {
        json()
    }

    routing {
        userRoutes(userRepository, qrCodeRepository)
    }
}
