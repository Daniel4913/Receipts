package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cleverexpenses.receipts.R
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditReceiptEvent
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditViewModel

@Composable
fun GeneralReceiptInputsHolder(
    focusRequester: FocusRequester,
    viewModel: AddEditViewModel
) {
    val localDensity = LocalDensity.current
    var componentHeight by remember { mutableStateOf(0.dp) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GeneralTextField(
            modifier = Modifier.weight(4f),
            value = viewModel.shopName.value.text,
            onValueChange = {
                viewModel.onEvent(
                    AddEditReceiptEvent.EnteredShopName(
                        it
                    )
                )
            },
            placeholderText = viewModel.shopName.value.placeholder,
            onFocusChanged = {
                viewModel.onEvent(
                    AddEditReceiptEvent.ChangeShopNameFocus(
                        it
                    )
                )
            },
            focusRequester = focusRequester
        )
        Surface(
            modifier = Modifier
                .width(1.dp)
                .height(componentHeight),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        ) {}
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Rounded.DateRange,
                contentDescription = "Select Date icon"
            )
        }

    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                componentHeight = with(localDensity) { it.size.height.toDp() }
            },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GeneralTextField(
            modifier = Modifier.weight(4f),
            value = viewModel.shopAddress.value.text,
            onValueChange = {
                viewModel.onEvent(
                    AddEditReceiptEvent.EnteredShopAddress(
                        it
                    )
                )
            },
            placeholderText = viewModel.shopAddress.value.placeholder,
            onFocusChanged = {
                viewModel.onEvent(
                    AddEditReceiptEvent.ChangeShopAddressFocus(
                        it
                    )
                )
            },
            focusRequester = focusRequester
        )
        Surface(
            modifier = Modifier
                .width(1.dp)
                .height(componentHeight),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        ) {}
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.round_credit_card_24),
                contentDescription = "Select payment method icon"
            )
        }

    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GeneralTextField(
            modifier = Modifier.weight(4f),
            value = viewModel.sum.value.text,
            onValueChange = { viewModel.onEvent(AddEditReceiptEvent.EnteredSum(it)) },
            placeholderText = viewModel.sum.value.placeholder,
            onFocusChanged = { viewModel.onEvent(AddEditReceiptEvent.ChangeSumFocus(it)) },
            focusRequester = focusRequester,
            trailingIcon = { Text(text = "PLN") }
        )
        Surface(
            modifier = Modifier
                .width(1.dp)
                .height(componentHeight),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        ) {}
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.round_currency_exchange_24),
                contentDescription = "Currency icon"
            )
        }
    }
}