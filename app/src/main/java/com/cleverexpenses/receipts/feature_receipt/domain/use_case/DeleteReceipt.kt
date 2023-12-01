package com.cleverexpenses.receipts.feature_receipt.domain.use_case

import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt

class DeleteReceipt(private val repository: ReceiptRepository) {
    // czy robienie osobnego use case do pobierania tylko Receipt bez produktów daje jakąś przewagę, nie wiem, w performance np.?
    suspend operator fun invoke(receipt: Receipt) {
        repository.deleteReceipt(receipt)
    }
}