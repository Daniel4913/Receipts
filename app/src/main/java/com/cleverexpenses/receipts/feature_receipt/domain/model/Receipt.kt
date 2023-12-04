package com.cleverexpenses.receipts.feature_receipt.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Receipt(
    @PrimaryKey val receiptId: Int,
    val shopName: String,
    val date: String,
    val sum: Int,
    val paymentMethod: String,
)