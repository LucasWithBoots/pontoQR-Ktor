package LucasWithBoots.github.io.repositories.Usuario

import LucasWithBoots.github.io.mapping.UsuarioDAO
import LucasWithBoots.github.io.mapping.UsuarioTable
import LucasWithBoots.github.io.mapping.daoToModel
import LucasWithBoots.github.io.mapping.suspendTransaction
import LucasWithBoots.github.io.model.Usuario
import at.favre.lib.crypto.bcrypt.BCrypt
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

// Opções de requisições: https://ktor.io/docs/server-integrate-database.html#create-new-repo

class PostgresUsuarioRepository : UsuarioRepository {
    override suspend fun allUsuarios(): List<Usuario> = suspendTransaction {
        UsuarioDAO.all().map(::daoToModel)
    }

    override suspend fun usuarioById(id: Int): Usuario? = suspendTransaction {
        UsuarioDAO.find { (UsuarioTable.id eq id) }.limit(1).map(::daoToModel).firstOrNull()
    }

    override suspend fun addUsuario(usuario: Usuario): Unit = suspendTransaction {

        var senhaHash = BCrypt.withDefaults().hashToString(12, usuario.senha.toCharArray())

        UsuarioDAO.new {
            nome = usuario.nome
            email = usuario.email
            senha = senhaHash
            ehcriador = usuario.ehcriador
            data_criacao = usuario.data_criacao
        }
    }

    override suspend fun removeUsuario(id: Int): Boolean = suspendTransaction {
        val rowsDeleted = UsuarioTable.deleteWhere {
            UsuarioTable.id eq id
        }
        rowsDeleted == 1
    }
}