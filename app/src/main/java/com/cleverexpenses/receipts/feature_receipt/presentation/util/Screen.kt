package com.cleverexpenses.receipts.feature_receipt.presentation.util

import com.cleverexpenses.receipts.feature_receipt.presentation.util.Constants.ADD_EDIT_SCREEN_ARGUMENT_KEY
import com.cleverexpenses.receipts.feature_receipt.presentation.util.Constants.RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY

sealed class Screen(val route: String) {
    object ReceiptsScreen : Screen("receipts_screen")
    object AddEditScreen : Screen(
        route = "add_edit_screen?$ADD_EDIT_SCREEN_ARGUMENT_KEY=" +
                "{$ADD_EDIT_SCREEN_ARGUMENT_KEY}"
    ) {
        fun passReceiptId(receiptId: Int) =
            "add_edit_screen?$ADD_EDIT_SCREEN_ARGUMENT_KEY=$receiptId"
    }

    object ReceiptDetailsScreen : Screen(
        route = "receipt_details_screen/$RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY=" +
                "{$RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY}"
    ) {
        fun passReceiptId(receiptId: Int) =
            "receipt_details_screen/$RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY=$receiptId"
    }
}
