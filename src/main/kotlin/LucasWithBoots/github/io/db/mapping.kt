package LucasWithBoots.github.io.db

import LucasWithBoots.github.io.model.Usuario
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.kotlin.datetime.date
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object UsuarioTable : IntIdTable("usuario") {
    val nome = varchar("nome", 255)
    val email = varchar("email", 255)
    val ehcriador = bool("ehcriador")
    val data_criacao = date("data_criacao")
}

class UsuarioDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UsuarioDAO>(UsuarioTable)

    var nome by UsuarioTable.nome
    var email by UsuarioTable.email
    var ehcriador by UsuarioTable.ehcriador
    var data_criacao by UsuarioTable.data_criacao
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

fun daoToModel(dao: UsuarioDAO) = Usuario(
    dao.id.value,
    dao.nome,
    dao.email,
    dao.ehcriador,
    dao.data_criacao
)