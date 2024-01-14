package com.cleverexpenses.receipts.feature_receipt.presentation.receipt_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptDetailsScreen(
    viewModel: ReceiptDetailsViewModel,
    navigateBack: () -> Unit,
    navigateToAddEditReceiptWithArgs: (Int) -> Unit,
) {
    var paddingValues: PaddingValues


    Scaffold {
        paddingValues = it
        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
        ) {
            Text(text = "Receipt Details")
            Text(
                text = "${viewModel.state.receiptId}"
            )
        }
    }
}