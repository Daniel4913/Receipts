package com.cleverexpenses.receipts.feature_receipt.presentation.receipt_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.ReceiptUseCases
import com.cleverexpenses.receipts.feature_receipt.presentation.util.Constants.RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY

class ReceiptDetailsViewModel(
    val useCases: ReceiptUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(ReceiptDetailsState())

    init {
        getReceiptIdArgument()
    }

    private fun getReceiptIdArgument() {
        state = state.copy(
            receiptId = savedStateHandle.get<Int>(
                key = RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY
            )!!
        )

    }
}

data class ReceiptDetailsState(
    val receiptId: Int = 0,
    val isLoading: Boolean = false,
    val error: String = ""
)