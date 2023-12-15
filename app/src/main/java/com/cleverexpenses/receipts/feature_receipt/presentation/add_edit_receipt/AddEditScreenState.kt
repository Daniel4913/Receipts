package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import android.net.Uri
import java.time.ZonedDateTime

data class AddEditScreenState(
    val text: String = "",
    val placeholder: String = "",
    val isPlaceholderVisible: Boolean = true,
    val error: String = "",
    val isInteger: Boolean = false,
    val receiptDateTime: ZonedDateTime = ZonedDateTime.now(),
    val isSaveEnabled: Boolean = true,
    val receiptImageUri: Uri? = null
)