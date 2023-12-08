package com.cleverexpenses.receipts.feature_receipt.domain.use_case

import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.model.ReceiptWithProducts
import kotlinx.coroutines.flow.Flow

class GetAllReceiptsWithProducts(
    private val repository: ReceiptRepository
) {
    operator fun invoke(): Flow<List<ReceiptWithProducts>> {
        return repository.getAllReceiptsWithProducts()
    }
}