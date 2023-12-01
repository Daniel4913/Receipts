package com.cleverexpenses.receipts.feature_receipt.data.datasource

import androidx.room.Embedded
import androidx.room.Relation
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt

data class ReceiptWithProducts(
    @Embedded val receipt: Receipt,
    @Relation(
        parentColumn = "receiptId",
        entityColumn = "productId"
    )
    val product: List<Product>
)