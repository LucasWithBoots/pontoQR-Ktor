package LucasWithBoots.github.io.model

import LucasWithBoots.github.io.db.UsuarioDAO
import LucasWithBoots.github.io.db.UsuarioTable
import LucasWithBoots.github.io.db.daoToModel
import LucasWithBoots.github.io.db.suspendTransaction
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
        UsuarioDAO.new {
            nome = usuario.nome
            email = usuario.email
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