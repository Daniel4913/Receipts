package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.util

import androidx.compose.runtime.Composable

data class FromIconDropdownOption(
    val label: String,
    val leadingIcon: @Composable () -> Unit,
    val description: String = ""
)