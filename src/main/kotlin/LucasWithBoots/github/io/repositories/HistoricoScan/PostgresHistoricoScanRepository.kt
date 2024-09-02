package LucasWithBoots.github.io.repositories.HistoricoScan

import LucasWithBoots.github.io.mapping.*
import LucasWithBoots.github.io.model.HistoricoScan
import LucasWithBoots.github.io.model.HistoricoScanCompleto
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class PostgresHistoricoScanRepository: HistoricoScanRepository {
    override suspend fun allHistoricoScan(): List<HistoricoScan> = suspendTransaction {
        HistoricoScanDAO.all().map(::daoToModel)
    }

    override suspend fun historicoScanById(id: Int): HistoricoScan? = suspendTransaction {
        HistoricoScanDAO.find { (HistoricoScanTable.id eq id) }.limit(1).map(::daoToModel).firstOrNull()
    }

    override suspend fun historicoScanByIdCompleto(id: Int): Any = suspendTransaction {
        val historico = HistoricoScanDAO.find { (HistoricoScanTable.id eq id) }.limit(1).map(::daoToModel).firstOrNull()

        val usuario = UsuarioDAO.find { (UsuarioTable.id eq historico?.id_usuario) }.limit(1).map(::daoToModel).firstOrNull()
        val qrCode = QrcodigoDAO.find { (QrcodigoTable.id eq historico?.id_qrcode) }.limit(1).map(::daoToModel).firstOrNull()

        HistoricoScanCompleto(
            historico?.id,
            usuario,
            qrCode = TODO(),
            data_scan = TODO(),
        )
    }

    override suspend fun addHistoricoScan(historicoScan: HistoricoScan): Unit = suspendTransaction {
        HistoricoScanDAO.new {
            id_usuario = historicoScan.id_usuario
            id_qrcode = historicoScan.id_qrcode
            data_scan = historicoScan.data_scan
        }
    }

    override suspend fun historicoScanByUserId(id: Int): List<HistoricoScan> = suspendTransaction {
        HistoricoScanDAO.find { (HistoricoScanTable.id_usuario eq id) }.map(::daoToModel)
    }

    override suspend fun historicoScanByQrCodeId(id: Int): List<HistoricoScan> = suspendTransaction {
        HistoricoScanDAO.find { (HistoricoScanTable.id_usuario eq id) }.map(::daoToModel)
    }
}