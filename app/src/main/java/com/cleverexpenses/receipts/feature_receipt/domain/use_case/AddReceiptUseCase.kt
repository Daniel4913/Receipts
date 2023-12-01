package com.cleverexpenses.receipts.feature_receipt.domain.use_case

import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt

class AddReceiptUseCase(
    private val repository: ReceiptRepository
) {

    suspend operator fun invoke(receipt: Receipt) {
        if (receipt.shopName.isBlank()) {
            throw Exception("shop name empty")
        }
        repository.insertReceipt(receipt)
    }

}