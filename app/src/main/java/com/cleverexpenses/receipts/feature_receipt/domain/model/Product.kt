package com.cleverexpenses.receipts.feature_receipt.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Receipt::class,
        parentColumns = ["receiptId"],
        childColumns = ["receiptOwnerId"]
    )]
)
data class Product(
    @PrimaryKey val productId: Int,
    val name: String,
    val quantity: Double,
    val unitPrice: Double,
    val totalPrice: Double,
    val receiptOwnerId: Int
)
