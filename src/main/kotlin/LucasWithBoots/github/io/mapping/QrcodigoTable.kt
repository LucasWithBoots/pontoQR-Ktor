package LucasWithBoots.github.io.mapping

import LucasWithBoots.github.io.model.Qrcodigo
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object QrcodigoTable : IntIdTable("qrcodigo") {
    val id_usuariocriador = integer("id_usuariocriador").references(UsuarioTable.id)
    val titulo = varchar("titulo", 255)
    val descricao = varchar("descricao", 255)
    val data_criacao = date("data_criacao")
    val ativo = bool("ativo")
}

class QrcodigoDAO(id: EntityID<Int>) : IntEntity(id) { // DAO representa cada linha da tabela, enquanto QrcodigoTable é a tabela inteira
    companion object : IntEntityClass<QrcodigoDAO>(QrcodigoTable)

    var id_usuariocriador by QrcodigoTable.id_usuariocriador
    var titulo by QrcodigoTable.titulo
    var descricao by QrcodigoTable.descricao
    var data_criacao by QrcodigoTable.data_criacao
    var ativo by QrcodigoTable.ativo
}

fun daoToModel(dao: QrcodigoDAO) = Qrcodigo(
    dao.id.value,
    dao.id_usuariocriador,
    dao.titulo,
    dao.descricao,
    dao.data_criacao,
    dao.ativo
)