package LucasWithBoots.github.io.repositories.HistoricoScan

import LucasWithBoots.github.io.model.HistoricoScan
import LucasWithBoots.github.io.model.HistoricoScanCompleto

interface HistoricoScanRepository {
    suspend fun allHistoricoScan(): List<HistoricoScan>
    suspend fun historicoScanById(id: Int): HistoricoScan?
    suspend fun historicoScanByIdCompleto(id: Int): HistoricoScanCompleto?
    suspend fun addHistoricoScan(historicoScan: HistoricoScan)
    suspend fun historicoScanByUserId(id: Int): List<HistoricoScan>
    suspend fun historicoScanByQrCodeId(id: Int): List<HistoricoScan>
}