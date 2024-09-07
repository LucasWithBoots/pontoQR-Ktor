package com.example.repositories.qrcode

import com.example.mapping.QrCodeDAO
import com.example.mapping.QrCodes
import com.example.mapping.daoToModel
import com.example.model.QrCode
import com.example.plugins.suspendTransaction

class PostgresQrCodeRepository : QrCodeRepository {
    override suspend fun qrCodesByUserId(userId: Int): List<QrCode> = suspendTransaction {
        QrCodeDAO.find { (QrCodes.id_creator eq userId) }.map(::daoToModel)
    }
}