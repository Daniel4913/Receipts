package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.ReceiptUseCases
import com.cleverexpenses.receipts.feature_receipt.presentation.util.ZonedDateTimeConverter
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.ZonedDateTime

class AddEditViewModel(
    private val useCases: ReceiptUseCases
) : ViewModel() {

    private val _state = mutableStateOf(AddEditScreenState())
    val state: State<AddEditScreenState> = _state

    private val _shopName = mutableStateOf(
        AddEditScreenState(placeholder = "Shop name")
    )
    val shopName: State<AddEditScreenState> = _shopName

    private val _shopAddress = mutableStateOf(
        AddEditScreenState(placeholder = "Shop address")
    )
    val shopAddress: State<AddEditScreenState> = _shopAddress

    private val _receiptDate = mutableStateOf(
        AddEditScreenState(receiptDateTime = ZonedDateTime.now())
    )

    val receiptDate: State<AddEditScreenState> = _receiptDate

    private val _paymentMethod = mutableStateOf(
        AddEditScreenState(
            paymentMethod = "Cash"
        )
    )

    val paymentMethod: State<AddEditScreenState> = _paymentMethod

    private val _currency = mutableStateOf(
        AddEditScreenState(
            currency = "EUR"
        )
    )

    val currency: State<AddEditScreenState> = _currency

    private val _sum = mutableStateOf(
        AddEditScreenState(placeholder = "Sum")
    )
    val sum: State<AddEditScreenState> = _sum

    private val _receiptPhoto = mutableStateOf(
        AddEditScreenState(
            receiptImageUri = null
        )
    )


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

            is AddEditReceiptEvent.SaveReceiptImage -> {
                _receiptPhoto.value = _receiptPhoto.value.copy(
                    receiptImageUri = event.uri
                )
            }

            is AddEditReceiptEvent.ChangeReceiptDateTime -> {
                _receiptDate.value = _receiptDate.value.copy(
                    receiptDateTime = event.receiptDateTime
                )
            }

            is AddEditReceiptEvent.SaveReceipt -> {
                if (allInputsValid()) {
                    upsertReceipt(
                        onSuccess = { status ->
                            _state.value = _state.value.copy(
                                receiptSaved = status
                            )
                        },
                        onError = { message ->
                            _state.value = _state.value.copy(
                                error = Throwable(message)
                            )
                        }
                    )
                }
            }

            is AddEditReceiptEvent.SelectedCurrency -> {
                _currency.value = _currency.value.copy(
                    currency = event.value
                )
            }

            is AddEditReceiptEvent.SelectedPaymentMethod -> {
                _paymentMethod.value = _paymentMethod.value.copy(
                    paymentMethod = event.value
                )

            }
        }
    }

    private fun setErrorAndReturnFalse(message: String): Boolean {
        _state.value = _state.value.copy(
            error = Throwable(message)
        )
        return false
    }

    private fun allInputsValid(): Boolean {
        if (_shopName.value.text.isBlank()) {
            return setErrorAndReturnFalse("Shop name cannot be empty")
        }
        if (_sum.value.text.isBlank()) {
            return setErrorAndReturnFalse("Sum cannot be empty")
        } else {
            _sum.value.text.toDoubleOrNull()
                ?: return setErrorAndReturnFalse("Sum is not a number")
        }
        return true
    }

    private fun upsertReceipt(onSuccess: (Boolean) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                useCases.addReceipt(
                    Receipt(
                        shopName = _shopName.value.text,
                        shopAddress = _shopAddress.value.text,
                        receiptDate = ZonedDateTimeConverter().toTimestamp(_receiptDate.value.receiptDateTime),
                        createDate = System.currentTimeMillis(),
                        sum = _sum.value.text.toDouble(),
                        paymentMethod = _paymentMethod.value.paymentMethod,
                        currency = _currency.value.currency,
                        receiptPhotoUri = _receiptPhoto.value.receiptImageUri.toString()
                    )
                )
                onSuccess(true)
                Timber.d("Receipt saved: ${_shopName.value.text} \n${_shopAddress.value.text} \n${_sum.value.text} \n${_receiptDate.value.receiptDateTime}")

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e
                )
                onError(e.message ?: "Unknown error")
                Timber.e("Receipt not saved: $e")
            }
        }
    }

}