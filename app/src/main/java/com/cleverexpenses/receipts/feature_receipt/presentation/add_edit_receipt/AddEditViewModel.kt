package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
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

    private val _sum = mutableStateOf(
        AddEditScreenState(placeholder = "Sum", isInteger = true)
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
                    receiptImageUri = event.file.toUri()
                )
            }

            is AddEditReceiptEvent.ChangeReceiptDateTime -> {
                _receiptDate.value = _receiptDate.value.copy(
                    receiptDateTime = event.receiptDateTime
                )
            }

            is AddEditReceiptEvent.SaveReceipt -> {
                viewModelScope.launch {
                    try {
                        useCases.addReceipt(
                            Receipt(
                                shopName = _shopName.value.text,
                                shopAddress = _shopAddress.value.text,
                                receiptDate = ZonedDateTimeConverter().toTimestamp(_receiptDate.value.receiptDateTime),
                                createDate = System.currentTimeMillis(),
                                sum = _sum.value.text.toInt(),
                                paymentMethod = "",
                                currency = "",
                                receiptPhotoUri = _receiptPhoto.value.receiptImageUri.toString()
                            )
                        )
                        Timber.d("Receipt saved: ${_shopName.value.text} \n${_shopAddress.value.text} \n${_sum.value.text} \n${_receiptDate.value.receiptDateTime}")
                    } catch (e: Exception) {
                        Timber.e("Receipt not saved: $e")
                    }
                }
            }
        }
    }

}