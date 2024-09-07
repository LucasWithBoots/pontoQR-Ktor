package com.example.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class QrCode(
    val id: Int,
    val creator: User,
    val team: Team,
    val title: String,
    val description: String,
    val date_created: LocalDateTimeAsString = LocalDateTime.now(),
    val active: Boolean
)
