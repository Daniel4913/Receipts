package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import androidx.compose.ui.focus.FocusState
import java.io.File
import java.time.ZonedDateTime

sealed class AddEditReceiptEvent {
    data class EnteredShopName(val value: String) : AddEditReceiptEvent()
    data class ChangeShopNameFocus(val focusState: FocusState) : AddEditReceiptEvent()
    data class EnteredShopAddress(val value: String) : AddEditReceiptEvent()
    data class ChangeShopAddressFocus(val focusState: FocusState) : AddEditReceiptEvent()
    data class EnteredSum(val value: String) : AddEditReceiptEvent()
    data class ChangeSumFocus(val focusState: FocusState) : AddEditReceiptEvent()

    data class ChangeReceiptDateTime(val receiptDateTime: ZonedDateTime) : AddEditReceiptEvent()
    data class SaveReceiptImage(val file: File) : AddEditReceiptEvent()
    object SaveReceipt : AddEditReceiptEvent()

}
