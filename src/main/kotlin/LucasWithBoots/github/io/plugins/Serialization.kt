package LucasWithBoots.github.io.plugins

import LucasWithBoots.github.io.model.PostgresUsuarioRepository
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization(repository: PostgresUsuarioRepository) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        route("/usuario") {
            get {
                val usuarios = repository.allUsuarios()
                call.respond(usuarios)
            }
        }
    }
}
