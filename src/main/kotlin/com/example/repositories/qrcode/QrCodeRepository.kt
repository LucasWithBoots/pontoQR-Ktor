package com.example.repositories.qrcode

import com.example.model.QrCode

interface QrCodeRepository {
    suspend fun qrCodesByUserId(userId: Int): List<QrCode>
}