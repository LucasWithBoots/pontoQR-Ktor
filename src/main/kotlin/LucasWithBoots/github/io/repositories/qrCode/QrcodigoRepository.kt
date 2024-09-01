package LucasWithBoots.github.io.repositories.qrCode

import LucasWithBoots.github.io.model.Qrcodigo

interface QrcodigoRepository {
    suspend fun allQrcodigo(): List<Qrcodigo>
    suspend fun qrcodigoById(id: Int): Qrcodigo?
    suspend fun addQrcodigo(qrcodigo: Qrcodigo)
    suspend fun removeQrcodigo(id: Int): Boolean
}