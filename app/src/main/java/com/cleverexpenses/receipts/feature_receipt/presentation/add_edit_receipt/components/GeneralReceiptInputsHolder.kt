package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cleverexpenses.receipts.R
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditReceiptEvent
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditViewModel
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.util.FromIconDropdownOption
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralReceiptInputsHolder(
    focusRequester: FocusRequester,
    viewModel: AddEditViewModel,
    onDateTimeUpdated: (ZonedDateTime) -> Unit
) {
    val localDensity = LocalDensity.current
    var componentHeight by remember { mutableStateOf(0.dp) }

    val dateDialog = rememberSheetState()
    val timeDialog = rememberSheetState()
    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    var currentTime by remember { mutableStateOf(LocalTime.now()) }


    val currencyOptions = listOf(
        FromIconDropdownOption(label = "Złoty", leadingIcon = { Text(text = "PLN") }),
        FromIconDropdownOption(label = "Euro", leadingIcon = { Text(text = "EUR") }),
    )

    val paymentOptions = listOf(
        FromIconDropdownOption(label = "Cash", leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.round_attach_money_24),
                contentDescription = "Cash payment method icon"
            )
        }
        ),
        FromIconDropdownOption(label = "Card", leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.round_credit_card_24),
                contentDescription = "Card payment method icon"
            )
        })
    )


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
            onClick = {
                dateDialog.show()
            }) {
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
        FromIconDropdown(
            modifier = Modifier.weight(1f),
            options = paymentOptions,
            selectedOption = paymentOptions[0],
            onOptionSelected = {
                viewModel.onEvent(AddEditReceiptEvent.SelectedPaymentMethod(it.label))
            },
        )

    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GeneralTextField(
            modifier = Modifier.weight(4f),
            value = getValidatedDecimal(viewModel.sum.value.text),
            onValueChange = { viewModel.onEvent(AddEditReceiptEvent.EnteredSum(it)) },
            placeholderText = viewModel.sum.value.placeholder,
            onFocusChanged = { viewModel.onEvent(AddEditReceiptEvent.ChangeSumFocus(it)) },
            focusRequester = focusRequester,
            keyboardType = KeyboardType.Number
        )
        Surface(
            modifier = Modifier
                .width(1.dp)
                .height(componentHeight),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        ) {}
        FromIconDropdown(
            modifier = Modifier.weight(1f),
            options = currencyOptions,
            selectedOption = currencyOptions[0],
            onOptionSelected = {
                viewModel.onEvent(AddEditReceiptEvent.SelectedCurrency(it.label))
            },
        )
    }

    CalendarDialog(state = dateDialog, selection = CalendarSelection.Date { localDate ->
        currentDate = localDate
        timeDialog.show()
    }, config = CalendarConfig(monthSelection = true, yearSelection = true))

    ClockDialog(
        state = timeDialog,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            currentTime = LocalTime.of(hours, minutes)
            onDateTimeUpdated(
                ZonedDateTime.of(
                    currentDate,
                    currentTime,
                    ZoneId.systemDefault()
                )
            )
        },
        config = ClockConfig(
            is24HourFormat = true
        )
    )
}

fun getValidatedDecimal(text: String): String {
    if (text.isEmpty()) return text

    val normalizedText = text.replace(',', '.')
    val filteredChars = normalizedText.filterIndexed { index, c ->
        c.isDigit()
                || (c == '.' && index != 0 && normalizedText.indexOf('.') == index)
                || (c == '.' && index != 0 && normalizedText.count { it == '.' } <= 1)
    }

    // If dot is present, take digits before decimal and first 2 digits after decimal
    return if (filteredChars.count { it == '.' } == 1) {
        val beforeDecimal = filteredChars.substringBefore('.')
        val afterDecimal = filteredChars.substringAfter('.')
        beforeDecimal + "." + afterDecimal.take(2)
    }
    // If there is no dot, return the digits
    else {
        filteredChars
    }
}