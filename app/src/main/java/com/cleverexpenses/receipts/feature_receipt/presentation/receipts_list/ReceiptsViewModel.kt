package com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.ReceiptUseCases
import kotlinx.coroutines.launch


class ReceiptsViewModel(
    private val receiptUseCases: ReceiptUseCases
) : ViewModel() {

    private val _ListState = mutableStateOf(
        ListState(
            receipts = emptyList(),
            isLoading = false,
            error = ""
        )
    )

    val listState: ListState
        get() = _ListState.value

    init {
        getReceipts()
    }

    private fun getReceipts() {
        viewModelScope.launch {
            receiptUseCases.getAllReceiptsWithProducts.invoke().collect { receipts ->
                _ListState.value = listState.copy(
                    receipts = receipts,
                    isLoading = false
                )
            }
        }
    }
}
