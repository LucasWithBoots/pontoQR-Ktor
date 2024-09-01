package LucasWithBoots.github.io.repositories.Usuario

import LucasWithBoots.github.io.model.Usuario

interface UsuarioRepository {
    suspend fun allUsuarios(): List<Usuario>
    suspend fun usuarioById(id: Int): Usuario?
    suspend fun addUsuario(usuario: Usuario)
    suspend fun removeUsuario(id: Int): Boolean
}