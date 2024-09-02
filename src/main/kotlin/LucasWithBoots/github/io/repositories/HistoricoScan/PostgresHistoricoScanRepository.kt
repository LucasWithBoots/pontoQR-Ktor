package LucasWithBoots.github.io.repositories.HistoricoScan

import LucasWithBoots.github.io.mapping.*
import LucasWithBoots.github.io.model.HistoricoScan
import LucasWithBoots.github.io.model.HistoricoScanCompleto
import LucasWithBoots.github.io.repositories.Qrcodigo.QrcodigoRepository
import LucasWithBoots.github.io.repositories.Usuario.UsuarioRepository
import kotlinx.coroutines.runBlocking

class PostgresHistoricoScanRepository(
    private val usuarioRepository: UsuarioRepository,
    private val qrcodigoRepository: QrcodigoRepository
): HistoricoScanRepository{
    override suspend fun allHistoricoScan(): List<HistoricoScan> = suspendTransaction {
        HistoricoScanDAO.all().map(::daoToModel)
    }

    override suspend fun historicoScanById(id: Int): HistoricoScan? = suspendTransaction {
        HistoricoScanDAO.find { (HistoricoScanTable.id eq id) }.limit(1).map(::daoToModel).firstOrNull()
    }

    override suspend fun historicoScanByIdCompleto(id: Int): HistoricoScanCompleto? = suspendTransaction {
        val historicoScanDAO = HistoricoScanDAO.find { (HistoricoScanTable.id eq id) }.limit(1).map(::daoToModel).firstOrNull()

            runBlocking{
                    val usuario = usuarioRepository.usuarioById(historicoScanDAO!!.id_usuario)
                    val qrcodigo = qrcodigoRepository.qrcodigoById(historicoScanDAO.id_qrcode)

                    HistoricoScanCompleto(
                        id = historicoScanDAO.id,
                        usuario = usuario,
                        qrCode = qrcodigo,
                        data_scan = historicoScanDAO.data_scan
                    )
            }

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