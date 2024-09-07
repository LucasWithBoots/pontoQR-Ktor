package com.example.plugins.routing

import com.example.repositories.qrcode.QrCodeRepository
import com.example.repositories.user.UserRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.userRoutes(userRepository: UserRepository, qrCodeRepository: QrCodeRepository) {

    route("/users") {
        get {
            val users = userRepository.allUsers()

            if (users.isEmpty()) {
                call.respond(HttpStatusCode.NoContent, "No users found")
                return@get
            }

            call.respond(users)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing id")
                return@get
            }

            val user = userRepository.userById(id)

            if (user == null) {
                call.respond(HttpStatusCode.NotFound, "User not found")
                return@get
            }

            call.respond(user)
        }

        get("/{id}/qrcodes") {
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing id")
                return@get
            }

            val user = userRepository.userById(id)

            if (user == null) {
                call.respond(HttpStatusCode.NotFound, "User not found")
                return@get
            }

            val qrCodes = qrCodeRepository.qrCodesByUserId(id)

            call.respond(qrCodes)
        }
    }
}