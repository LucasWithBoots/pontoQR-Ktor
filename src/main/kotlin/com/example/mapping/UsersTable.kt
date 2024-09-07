package com.example.mapping

import com.example.model.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Users: IntIdTable("users"){
    val id_team = reference("id_team", Teams)
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val is_creator = bool("is_creator")
    val date_created = datetime("date_created")
    val active = bool("active")
}

class UserDAO(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<UserDAO>(Users)

    var id_team by TeamDAO referencedOn Users.id_team
    var name by Users.name
    var email by Users.email
    var password by Users.password
    var is_creator by Users.is_creator
    var date_created by Users.date_created
    var active by Users.active
}

fun daoToModel(dao: UserDAO) = User(
    id = dao.id.value,
    team = daoToModel(dao.id_team),
    name = dao.name,
    email = dao.email,
    password = dao.password,
    is_creator = dao.is_creator,
    date_created = dao.date_created,
    active = dao.active
)