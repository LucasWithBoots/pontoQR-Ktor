package com.example.mapping

import com.example.model.QrCode
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object QrCodes: IntIdTable("qrcodes"){
    val id_creator = reference("id_creator", Users)
    val id_team = reference("id_team", Teams)
    val title = varchar("title", 255)
    val description = varchar("description", 255)
    val date_created = datetime("date_created")
    val active = bool("active")
}

class QrCodeDAO(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<QrCodeDAO>(QrCodes)

    var id_creator by UserDAO referencedOn QrCodes.id_creator
    var id_team by TeamDAO referencedOn QrCodes.id_team
    var title by QrCodes.title
    var description by QrCodes.description
    var date_created by QrCodes.date_created
    var active by QrCodes.active
}

fun daoToModel(dao: QrCodeDAO) = QrCode(
    id = dao.id.value,
    creator = daoToModel(dao.id_creator),
    team = daoToModel(dao.id_team),
    title = dao.title,
    description = dao.description,
    date_created = dao.date_created,
    active = dao.active
)