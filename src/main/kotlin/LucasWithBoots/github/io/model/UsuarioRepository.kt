package LucasWithBoots.github.io.model

interface UsuarioRepository {
    suspend fun allUsuarios(): List<Usuario>
    suspend fun addUsuario(usuario: Usuario)
    suspend fun removeUsuario(id: Int): Boolean
}