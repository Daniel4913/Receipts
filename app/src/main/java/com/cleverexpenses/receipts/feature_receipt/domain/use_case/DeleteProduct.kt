package com.cleverexpenses.receipts.feature_receipt.domain.use_case

import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product

class DeleteProduct(private val repository: ReceiptRepository) {

    suspend operator fun invoke(product: Product) {
        repository.insertProduct(product)
    }
}