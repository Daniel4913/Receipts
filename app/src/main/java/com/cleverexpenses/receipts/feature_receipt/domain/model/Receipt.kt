package com.cleverexpenses.receipts.feature_receipt.domain.model

import com.cleverexpenses.receipts.feature_receipt.domain.util.Payment

data class Receipt(
    val id: Int,
    val shopName: String,
    val date: String,
    val products: List<Product>,
    val sum: Int,
    val paymentMethod: Payment,
)
