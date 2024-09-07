package com.example.model

import java.time.LocalDateTime

data class ScanHistory(
    val id: Int,
    val user: User,
    val qrcode: QrCode,
    val scan_time: LocalDateTimeAsString = LocalDateTime.now(),
    val location: String
)