package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import com.cleverexpenses.receipts.R
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.components.GeneralTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    viewModel: AddEditViewModel,
    onBackPressed: () -> Unit
) {
    var paddingValues: PaddingValues

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Receipt") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Navigate back icon"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_save_24),
                    contentDescription = "Save Receipt",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    ) { paddingVal ->
        paddingValues = paddingVal
        val focusRequester = FocusRequester()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GeneralTextField(
                modifier = Modifier,
                value = viewModel.shopName.value.text,
                onValueChange = { viewModel.onEvent(AddEditReceiptEvent.EnteredShopName(it)) },
                placeholderText = viewModel.shopName.value.placeholder,
                onFocusChanged = { viewModel.onEvent(AddEditReceiptEvent.ChangeShopNameFocus(it)) },
                focusRequester = focusRequester
            )
            GeneralTextField(
                modifier = Modifier,
                value = viewModel.shopAddress.value.text,
                onValueChange = { viewModel.onEvent(AddEditReceiptEvent.EnteredShopAddress(it)) },
                placeholderText = viewModel.shopAddress.value.placeholder,
                onFocusChanged = { viewModel.onEvent(AddEditReceiptEvent.ChangeShopAddressFocus(it)) },
                focusRequester = focusRequester
            )
            GeneralTextField(
                modifier = Modifier,
                value = viewModel.sum.value.text,
                onValueChange = { viewModel.onEvent(AddEditReceiptEvent.EnteredSum(it)) },
                placeholderText = viewModel.sum.value.placeholder,
                onFocusChanged = { viewModel.onEvent(AddEditReceiptEvent.ChangeSumFocus(it)) },
                focusRequester = focusRequester
            )
        }
    }
}