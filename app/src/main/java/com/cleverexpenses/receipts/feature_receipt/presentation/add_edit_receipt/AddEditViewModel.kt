package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.ReceiptUseCases

class AddEditViewModel(
    useCases: ReceiptUseCases
) : ViewModel() {
    private val _shopName = mutableStateOf(
        GeneralTextFieldState(placeholder = "Shop name")
    )
    val shopName: State<GeneralTextFieldState> = _shopName

    private val _shopAddress = mutableStateOf(
        GeneralTextFieldState(placeholder = "Shop address")
    )
    val shopAddress: State<GeneralTextFieldState> = _shopAddress

    private val _sum = mutableStateOf(
        GeneralTextFieldState(placeholder = "Sum", isInteger = true)
    )
    val sum: State<GeneralTextFieldState> = _sum

    fun onEvent(event: AddEditReceiptEvent) {
        when (event) {
            is AddEditReceiptEvent.EnteredShopName -> {
                _shopName.value = _shopName.value.copy(
                    text = event.value
                )
            }

            is AddEditReceiptEvent.ChangeShopNameFocus -> {
                _shopName.value = _shopName.value.copy(
                    isPlaceholderVisible = !event.focusState.isFocused && _shopName.value.text.isBlank()
                )
            }

            is AddEditReceiptEvent.EnteredShopAddress -> {
                _shopAddress.value = _shopAddress.value.copy(
                    text = event.value
                )
            }

            is AddEditReceiptEvent.ChangeShopAddressFocus -> {
                _shopAddress.value = _shopAddress.value.copy(
                    isPlaceholderVisible = !event.focusState.isFocused && _shopAddress.value.text.isBlank()
                )
            }

            is AddEditReceiptEvent.EnteredSum -> {
                _sum.value = _sum.value.copy(
                    text = event.value
                )
            }

            is AddEditReceiptEvent.ChangeSumFocus -> {
                _sum.value = _sum.value.copy(
                    isPlaceholderVisible = !event.focusState.isFocused && _sum.value.text.isBlank()
                )
            }

            is AddEditReceiptEvent.SaveReceipt -> {
                TODO()
            }
        }
    }

}