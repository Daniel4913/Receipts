package com.cleverexpenses.receipts.feature_receipt.presentation.util

import com.cleverexpenses.receipts.R
import com.cleverexpenses.receipts.feature_receipt.domain.util.Payment

fun getPaymentMethod(paymentMethod: Payment): Int {
    return if (paymentMethod == Payment.Card) {
        R.drawable.round_credit_card_24
    } else {
        R.drawable.round_money_24
    }
}