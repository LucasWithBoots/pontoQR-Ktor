package LucasWithBoots.github.io

import LucasWithBoots.github.io.plugins.configureDatabases
import LucasWithBoots.github.io.plugins.configureRouting
import LucasWithBoots.github.io.plugins.configureSerialization
import LucasWithBoots.github.io.repositories.HistoricoScan.PostgresHistoricoScanRepository
import LucasWithBoots.github.io.repositories.Usuario.PostgresUsuarioRepository
import LucasWithBoots.github.io.repositories.Qrcodigo.PostgresQrcodigoRepository
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization(PostgresUsuarioRepository(), PostgresQrcodigoRepository(),PostgresHistoricoScanRepository(
        PostgresUsuarioRepository(), PostgresQrcodigoRepository()))
    configureDatabases()
    configureRouting()
}
