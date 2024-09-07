package com.example

import com.example.plugins.configureDatabases
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import com.example.repositories.qrcode.PostgresQrCodeRepository
import com.example.repositories.user.PostgresUserRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization(PostgresUserRepository(), PostgresQrCodeRepository())
    configureDatabases(environment.config)
    configureSecurity()
}
