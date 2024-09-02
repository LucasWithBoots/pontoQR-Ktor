package LucasWithBoots.github.io.model


import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Serializable

@Serializable
data class HistoricoScan(
    val id: Int? = null,
    val id_usuario: Int,
    val id_qrcode: Int,
    val data_scan: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
)

@Serializable
data class HistoricoScanCompleto(
    val id: Int? = null,
    val usuario: Usuario?,
    val qrCode: Qrcodigo?,
    val data_scan: LocalDate?
)