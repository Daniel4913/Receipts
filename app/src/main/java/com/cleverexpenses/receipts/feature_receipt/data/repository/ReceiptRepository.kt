package com.cleverexpenses.receipts.feature_receipt.data.repository

import com.cleverexpenses.receipts.feature_receipt.domain.model.ReceiptWithProducts
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import kotlinx.coroutines.flow.Flow

interface ReceiptRepository {

    fun getAllReceiptsWithProducts(): Flow<List<ReceiptWithProducts>>

    fun getReceiptWithProducts(receiptId: Int): Flow<ReceiptWithProducts>

    suspend fun insertReceipt(receipt: Receipt)

    suspend fun insertProduct(product: Product)

    suspend fun deleteReceipt(receipt: Receipt)

    suspend fun deleteProduct(product: Product)
}