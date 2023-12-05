package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

data class GeneralTextFieldState (
    val text: String = "",
    val placeholder :String = "",
    val isPlaceholderVisible: Boolean = true,
    val error: String = "",
    val isInteger: Boolean = false
)