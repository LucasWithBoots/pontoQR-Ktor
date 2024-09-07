package com.example.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class User (
    val id: Int,
    val team: Team,
    val name: String,
    val email: String,
    val password: String,
    val is_creator: Boolean,
    val date_created: LocalDateTimeAsString = LocalDateTime.now(),
    val active: Boolean
)