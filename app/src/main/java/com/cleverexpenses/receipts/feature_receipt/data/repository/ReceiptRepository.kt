package com.cleverexpenses.receipts.feature_receipt.data.repository

import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptWithProducts
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import kotlinx.coroutines.flow.Flow

interface ReceiptRepository {

    fun getReceipts(): Flow<List<Receipt>>

    suspend fun getReceiptWithProducts(receiptId: Int): ReceiptWithProducts

    suspend fun insertReceipt(receipt: Receipt)

    suspend fun insertProduct(product: Product)

}