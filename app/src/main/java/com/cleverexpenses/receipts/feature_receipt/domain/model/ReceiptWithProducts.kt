package com.cleverexpenses.receipts.feature_receipt.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class ReceiptWithProducts(
    @Embedded val general: Receipt,
    @Relation(
        parentColumn = "receiptId",
        entityColumn = "receiptOwnerId"
    )
    val products: List<Product>
)