package com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list

import com.cleverexpenses.receipts.feature_receipt.domain.model.ReceiptWithProducts

data class ListState(
    val receipts: List<ReceiptWithProducts>,
    val isLoading: Boolean,
    val error: String
)

