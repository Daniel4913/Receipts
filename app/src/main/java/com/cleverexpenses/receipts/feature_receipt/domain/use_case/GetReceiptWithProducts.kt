package com.cleverexpenses.receipts.feature_receipt.domain.use_case

import com.cleverexpenses.receipts.feature_receipt.domain.model.ReceiptWithProducts
import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import kotlinx.coroutines.flow.Flow

class GetReceiptWithProducts(
    private val repository: ReceiptRepository
) {
    operator fun invoke(receiptId: Int): Flow<ReceiptWithProducts> {
        return repository.getReceiptWithProducts(receiptId)
    }
}