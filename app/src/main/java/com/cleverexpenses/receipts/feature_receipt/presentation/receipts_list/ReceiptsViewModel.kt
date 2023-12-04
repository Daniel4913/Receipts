package com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.ReceiptUseCases
import kotlinx.coroutines.launch
import timber.log.Timber


class ReceiptsViewModel(
    private val receiptUseCases: ReceiptUseCases
) : ViewModel() {

    private val _receiptsListState = mutableStateOf(
        ReceiptsListState(
            receipts = emptyList(),
            isLoading = false,
            error = ""
        )
    )

    val receiptsListState: ReceiptsListState
        get() = _receiptsListState.value

    init {
        getReceipts()
    }

    private fun getReceipts() {
        viewModelScope.launch {
            receiptUseCases.getAllReceiptsWithProducts.invoke().collect { receipts ->
                _receiptsListState.value = receiptsListState.copy(
                    receipts = receipts,
                    isLoading = false
                )
            }
        }
    }
}
