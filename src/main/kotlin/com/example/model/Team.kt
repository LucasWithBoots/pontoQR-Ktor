package com.example.model

import io.ks3.java.time.LocalDateTimeAsStringSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

typealias LocalDateTimeAsString = @Serializable(LocalDateTimeAsStringSerializer::class) LocalDateTime

@Serializable
data class Team(
    val id: Int,
    val name: String,
    val date: LocalDateTimeAsString = LocalDateTime.now(),
    val active: Boolean
)
