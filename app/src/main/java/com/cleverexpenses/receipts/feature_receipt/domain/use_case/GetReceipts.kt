package com.cleverexpenses.receipts.feature_receipt.domain.use_case

import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import kotlinx.coroutines.flow.Flow

class GetReceipts(
    private val repository: ReceiptRepository
) {
    operator fun invoke(

    ): Flow<List<Receipt>> {
        return repository.getAllReceipts()
    }
}