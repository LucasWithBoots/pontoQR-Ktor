package LucasWithBoots.github.io

import LucasWithBoots.github.io.plugins.configureDatabases
import LucasWithBoots.github.io.plugins.configureRouting
import LucasWithBoots.github.io.plugins.configureSecurity
import LucasWithBoots.github.io.plugins.configureSerialization
import LucasWithBoots.github.io.repositories.HistoricoScan.PostgresHistoricoScanRepository
import LucasWithBoots.github.io.repositories.Qrcodigo.PostgresQrcodigoRepository
import LucasWithBoots.github.io.repositories.Usuario.PostgresUsuarioRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization(
        PostgresUsuarioRepository(), PostgresQrcodigoRepository(), PostgresHistoricoScanRepository(
            PostgresUsuarioRepository(), PostgresQrcodigoRepository()
        )
    )
    configureDatabases(environment.config)
    configureSecurity(environment.config)
    configureRouting()
}
