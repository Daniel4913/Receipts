package com.cleverexpenses.receipts.feature_receipt.presentation.receipt_details

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.ReceiptUseCases
import com.cleverexpenses.receipts.feature_receipt.domain.util.Payment
import com.cleverexpenses.receipts.feature_receipt.presentation.util.Constants.RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY
import com.cleverexpenses.receipts.feature_receipt.presentation.util.getPaymentMethodIcon
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel(
    val useCases: ReceiptUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(ReceiptDetailsState())

    init {
        getReceiptIdArgument()
        getReceiptDetails()
    }

    private fun getReceiptIdArgument() {
        state = state.copy(
            receiptId = savedStateHandle.get<Int>(
                key = RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY
            )!!
        )
    }

    private fun createPaymentMethodFromString(paymentMethodString: String): Payment {
        return if (paymentMethodString == Payment.Card.value) {
            Payment.Card
        } else {
            Payment.Cash
        }
    }

    fun getReceiptDetails() {
        state = state.copy(
            isLoading = true
        )
        viewModelScope.launch {
            useCases.getReceiptWithProducts(state.receiptId).collect { receipt ->
                state = state.copy(
                    receiptDisplayable = ReceiptDisplayable(
                        shopName = receipt.general.shopName,
                        shopAddress = receipt.general.shopAddress,
                        receiptDate = receipt.general.receiptDate.toString(),
                        sum = receipt.general.sum,
                        paymentMethod = createPaymentMethodFromString(receipt.general.paymentMethod),
                        currency = receipt.general.currency,
                        receiptPhotoUri = Uri.parse(receipt.general.receiptPhotoUri),
                        products = if (receipt.products.isNotEmpty()) {
                            receipt.products.map { product ->
                                ProductDisplayable(
                                    name = product.name
                                )
                            }
                        } else {
                            emptyList<ProductDisplayable>()
                        },
                    )
                )
                Timber.d("uri: ${state.receiptDisplayable.receiptPhotoUri}")
            }
            state = state.copy(
                isLoading = false
            )
        }
    }
}

data class ReceiptDetailsState(
    val receiptId: Int = 0,
    val receiptDisplayable: ReceiptDisplayable = ReceiptDisplayable(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class ReceiptDisplayable(
    val shopName: String = "",
    val shopAddress: String = "",
    val receiptDate: String = "",
    val sum: Double = 0.0,
    val paymentMethod: Payment = Payment.Cash,
    val currency: String = "",
    val receiptPhotoUri: Uri? = null,
    val products: List<ProductDisplayable> = emptyList(),
)

data class ProductDisplayable(
    val name: String,
)
