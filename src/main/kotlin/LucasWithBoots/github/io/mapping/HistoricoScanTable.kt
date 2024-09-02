package LucasWithBoots.github.io.mapping

import LucasWithBoots.github.io.model.HistoricoScan
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object HistoricoScanTable: IntIdTable("historico_scan") {
    val id_usuario = integer("id_usuario").references(UsuarioTable.id)
    val id_qrcode = integer("id_qrcode").references(QrcodigoTable.id)
    val data_scan = date("data_scan")
}

class HistoricoScanDAO(id: EntityID<Int>): IntEntity(id){
    companion object: IntEntityClass<HistoricoScanDAO>(HistoricoScanTable)

    var id_usuario by HistoricoScanTable.id_usuario
    var id_qrcode by HistoricoScanTable.id_qrcode
    var data_scan by HistoricoScanTable.data_scan
}

fun daoToModel(dao: HistoricoScanDAO) = HistoricoScan(
    dao.id.value,
    dao.id_usuario,
    dao.id_qrcode,
    dao.data_scan
)