package com.example

import com.example.plugins.*
import com.example.repositories.user.PostgresUserRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization(PostgresUserRepository())
    configureDatabases(environment.config)
    configureSecurity()
    configureRouting()
}
