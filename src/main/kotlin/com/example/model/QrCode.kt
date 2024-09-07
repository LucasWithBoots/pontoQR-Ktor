package com.example.model

import java.time.LocalDateTime

data class QrCode(
    val id: Int,
    val creator: User,
    val team: Team,
    val title: String,
    val description: String,
    val date_created: LocalDateTime,
    val active: Boolean
)
