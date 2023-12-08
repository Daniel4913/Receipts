package com.cleverexpenses.receipts.feature_receipt.data

import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptDao
import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import com.cleverexpenses.receipts.feature_receipt.domain.model.ReceiptWithProducts
import kotlinx.coroutines.flow.Flow

class ReceiptRepositoryImpl(
    private val receiptDao: ReceiptDao
) : ReceiptRepository {

    override fun getAllReceiptsWithProducts(): Flow<List<ReceiptWithProducts>> {
        return receiptDao.getAllReceiptsWithProducts()
    }

    override fun getReceiptWithProducts(receiptId: Int): Flow<ReceiptWithProducts> {
        return receiptDao.getReceiptWithProducts(receiptId)
    }

    override suspend fun insertReceipt(receipt: Receipt) {
        receiptDao.insertReceipt(receipt)
    }

    override suspend fun insertProduct(product: Product) {
        receiptDao.insertProduct(product)
    }

    override suspend fun deleteReceipt(receipt: Receipt) {
        receiptDao.deleteReceipt(receipt)
    }

    override suspend fun deleteProduct(product: Product) {
        receiptDao.deleteProduct(product)
    }

}