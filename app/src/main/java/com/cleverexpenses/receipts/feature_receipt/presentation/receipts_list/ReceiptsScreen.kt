package com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.components.ReceiptItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptsScreen(
    viewModel: ReceiptsViewModel,
    navigateToAddReceipt: () -> Unit,
    onReceiptClicked: (Int) -> Unit,
) {
    var paddingValues: PaddingValues

    val state = viewModel.listState

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToAddReceipt() }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Receipt")
            }
        }
    ) {
        paddingValues = it
        LazyColumn(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
        ) {
            items(state.receipts) { receipt ->
                ReceiptItem(
                    receipt = receipt,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = onReceiptClicked
                )
            }
        }
    }
}

