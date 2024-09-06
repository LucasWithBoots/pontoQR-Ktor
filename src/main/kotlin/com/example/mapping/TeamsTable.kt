package com.example.mapping

import com.example.model.Team
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Teams: IntIdTable("teams"){
    val name = varchar("name", 255)
    val date_created = datetime("date_created")
    val active = bool("active")
}

class TeamDAO(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<TeamDAO>(Teams)

    var name by Teams.name
    var date by Teams.date_created
    var active by Teams.active
}

fun daoToModel(dao: TeamDAO) = Team(
    id = dao.id.value,
    name = dao.name,
    date = dao.date,
    active = dao.active
)