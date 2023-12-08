package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.util.FromIconDropdownOption
import java.util.Locale

@Composable
fun FromIconDropdown(
    modifier: Modifier,
    options: List<FromIconDropdownOption>,
    selectedOption: FromIconDropdownOption,
    onOptionSelected: (FromIconDropdownOption) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedPaymentMethod by remember { mutableStateOf(selectedOption) }

    val iconButton = @Composable {
        IconButton(
            onClick = {
                expanded = true
            }) {
            selectedPaymentMethod.leadingIcon()
        }
    }
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        iconButton()
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize(Alignment.TopStart)
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedPaymentMethod = option
                    onOptionSelected(option)
                    expanded = false
                }, text = {
                    Row {
                        option.leadingIcon()
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(option.label.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        })
                    }
                })
            }
        }
    }
}