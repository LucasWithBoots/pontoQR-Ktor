package LucasWithBoots.github.io.plugins

import LucasWithBoots.github.io.model.Qrcodigo
import LucasWithBoots.github.io.model.Usuario
import LucasWithBoots.github.io.repositories.Usuario.PostgresUsuarioRepository
import LucasWithBoots.github.io.repositories.Qrcodigo.PostgresQrcodigoRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization(
    usuarioRepository: PostgresUsuarioRepository,
    qrcodigoRepository: PostgresQrcodigoRepository
) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        route("/usuario") {
            get {
                val usuarios = usuarioRepository.allUsuarios()
                call.respond(usuarios)
            }

            get("/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Missing id")
                    return@get
                }
                val usuario = usuarioRepository.usuarioById(id.toInt())
                if (usuario == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(usuario)
            }

            post {
                try {
                    val usuario = call.receive<Usuario>()
                    usuarioRepository.addUsuario(usuario)
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
                if (usuarioRepository.removeUsuario(id.toInt())) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }

        route("/qrcodigo") {
            get {
                val qrcodes = qrcodigoRepository.allQrcodigo()
                call.respond(qrcodes)
            }

            get("/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Missing id")
                    return@get
                }
                val qrcode = qrcodigoRepository.qrcodigoById(id.toInt())
                if (qrcode == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(qrcode)
            }

            post {
                try {
                    val qrcode = call.receive<Qrcodigo>()
                    qrcodigoRepository.addQrcodigo(qrcode)
                    call.respond(HttpStatusCode.Created)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            put("/ativar/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }

                qrcodigoRepository.ativarQrcodigo(id.toInt())
                call.respond(HttpStatusCode.OK)
            }

            delete("/{id}") {
                val id = call.parameters["id"]
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if (qrcodigoRepository.removeQrcodigo(id.toInt())) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}
