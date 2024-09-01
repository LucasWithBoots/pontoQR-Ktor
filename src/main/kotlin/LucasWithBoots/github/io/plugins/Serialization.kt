package LucasWithBoots.github.io.plugins

import LucasWithBoots.github.io.model.Usuario
import LucasWithBoots.github.io.repositories.Usuario.PostgresUsuarioRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
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

            get("/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Missing id")
                    return@get
                }
                val usuario = repository.usuarioById(id.toInt())
                if (usuario == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(usuario)
            }

            post {
                try {
                    val usuario = call.receive<Usuario>()
                    repository.addUsuario(usuario)
                    call.respond(HttpStatusCode.Created)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if (repository.removeUsuario(id.toInt())) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}
