package com.example.mapping

import com.example.model.ScanHistory
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object ScanHistories : IntIdTable("scan_histories") {
    val id_user = reference("id_user", Users)
    val id_qrcode = reference("id_qrcode", QrCodes)
    val scan_time = datetime("scan_time")
    val location = varchar("location", 255)
}

class ScanHistoryDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ScanHistoryDAO>(ScanHistories)

    var id_user by UserDAO referencedOn ScanHistories.id_user
    var id_qrcode by QrCodeDAO referencedOn ScanHistories.id_qrcode
    var scan_time by ScanHistories.scan_time
    var location by ScanHistories.location
}

fun daoToModel(dao: ScanHistoryDAO) = ScanHistory(
    id = dao.id.value,
    user = daoToModel(dao.id_user),
    qrcode = daoToModel(dao.id_qrcode),
    scan_time = dao.scan_time,
    location = dao.location
)