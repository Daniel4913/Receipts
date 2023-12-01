package com.cleverexpenses.receipts.feature_receipt.domain.use_case

import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptWithProducts
import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository

class GetReceiptWithProducts(
    private val repository: ReceiptRepository
) {
    suspend operator fun invoke(receiptId: Int) {
        repository.getReceiptWithProducts(receiptId)
    }
}