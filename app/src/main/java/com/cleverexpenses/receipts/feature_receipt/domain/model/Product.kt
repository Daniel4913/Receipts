package com.cleverexpenses.receipts.feature_receipt.domain.model

data class Product (
    val id: Int,
    val name: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalPrice: Int,
)
