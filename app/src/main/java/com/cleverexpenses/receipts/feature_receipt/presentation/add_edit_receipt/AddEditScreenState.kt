package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import android.net.Uri
import java.time.ZonedDateTime

data class AddEditScreenState(
    val text: String = "",
    val placeholder: String = "",
    val isPlaceholderVisible: Boolean = true,
    val error: Throwable? = null,
    val currency: String = "",
    val paymentMethod: String = "",
    val receiptDateTime: ZonedDateTime = ZonedDateTime.now(),
    val receiptSaved: Boolean = false,
    val receiptImageUri: Uri? = null
)