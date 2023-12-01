package com.cleverexpenses.receipts.feature_receipt.presentation.util

import com.cleverexpenses.receipts.R

fun getPaymentMethod(paymentMethod: String): Int {
    return if (paymentMethod == "card") {
        R.drawable.round_credit_card_24
    } else {
        R.drawable.round_money_24
    }
}