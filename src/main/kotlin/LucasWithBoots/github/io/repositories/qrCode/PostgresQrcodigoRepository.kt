package LucasWithBoots.github.io.repositories.qrCode

import LucasWithBoots.github.io.mapping.QrcodigoDAO
import LucasWithBoots.github.io.mapping.QrcodigoTable
import LucasWithBoots.github.io.mapping.daoToModel
import LucasWithBoots.github.io.mapping.suspendTransaction
import LucasWithBoots.github.io.model.Qrcodigo
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PostgresQrcodigoRepository : QrcodigoRepository {
    override suspend fun allQrcodigo(): List<Qrcodigo> = suspendTransaction {
        QrcodigoDAO.all().map(::daoToModel)
    }

    override suspend fun qrcodigoById(id: Int): Qrcodigo? = suspendTransaction {
        QrcodigoDAO.find { (QrcodigoTable.id eq id) }.limit(1).map(::daoToModel).firstOrNull()
    }

    override suspend fun addQrcodigo(qrcodigo: Qrcodigo): Unit = suspendTransaction {
        QrcodigoDAO.new {
            id_usuariocriador = qrcodigo.id_usuariocriador
            titulo = qrcodigo.titulo
            descricao = qrcodigo.descricao
            data_criacao = qrcodigo.data_criacao
            ativo = qrcodigo.ativo
        }
    }

    override suspend fun ativarQrcodigo(id: Int): Unit = suspendTransaction {
        val qrCodigo = QrcodigoDAO.findById(id)

        if (qrCodigo != null) {
            if (qrCodigo.ativo == true) {
                throw Exception("QR Code já está ativo")
            }
        }

        QrcodigoDAO.findByIdAndUpdate(id) {
            it.ativo = true
        }
    }

    override suspend fun removeQrcodigo(id: Int): Boolean = suspendTransaction {
        val rowsDeleted = QrcodigoTable.deleteWhere {
            QrcodigoTable.id eq id
        }
        rowsDeleted == 1
    }
}