package com.cleverexpenses.receipts.feature_receipt.domain.util

sealed class Payment {
    object Cash : Payment() {
        val value = "Cash"
    }

    object Card : Payment() {
        val value = "Card"
    }
}
