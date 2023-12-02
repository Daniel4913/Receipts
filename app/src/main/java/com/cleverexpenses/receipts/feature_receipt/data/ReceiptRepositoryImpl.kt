package com.cleverexpenses.receipts.feature_receipt.data

import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptDao
import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptWithProducts
import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import kotlinx.coroutines.flow.Flow

class ReceiptRepositoryImpl(
    private val dao: ReceiptDao
) : ReceiptRepository {
    override fun getAllReceipts(): Flow<List<Receipt>> {
        return dao.getAllReceipts()
    }

    override fun getAllReceiptsWithProducts(): Flow<List<ReceiptWithProducts>> {
        return dao.getAllReceiptsWithProducts()
    }

    override fun getReceiptWithProducts(receiptId: Int): Flow<ReceiptWithProducts> {
        return dao.getReceiptWithProducts(receiptId)
    }

    override suspend fun insertReceipt(receipt: Receipt) {
        dao.insertReceipt(receipt)
    }

    override suspend fun insertProduct(product: Product) {
        dao.insertProduct(product)
    }

    override suspend fun deleteReceipt(receipt: Receipt) {
        dao.deleteReceipt(receipt)
    }

    override suspend fun deleteProduct(product: Product) {
        dao.deleteProduct(product)
    }

}