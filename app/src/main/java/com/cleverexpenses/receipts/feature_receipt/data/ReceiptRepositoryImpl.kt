package com.cleverexpenses.receipts.feature_receipt.data

import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptDao
import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptWithProducts
import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import kotlinx.coroutines.flow.Flow

class ReceiptRepositoryImpl(
    private val dao: ReceiptDao
): ReceiptRepository {
    override fun getReceipts(): Flow<List<Receipt>> {
        TODO("Not yet implemented")
    }

    override suspend fun getReceiptWithProducts(receiptId: Int): ReceiptWithProducts {
        TODO("Not yet implemented")
    }

    override suspend fun insertReceipt(receipt: Receipt) {
        TODO("Not yet implemented")
    }

    override suspend fun insertProduct(product: Product) {
        TODO("Not yet implemented")
    }

}