package com.example.mapping

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object TeamTable: IntIdTable("team"){
    val name = varchar("name", 255)
    val date_created = datetime("date_created")
}