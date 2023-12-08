package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import com.canhub.cropper.CropImageContract
import com.cleverexpenses.receipts.R
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.components.GeneralReceiptInputsHolder
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.components.ImagePickerAndHolder
import timber.log.Timber
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    viewModel: AddEditViewModel,
    onBackPressed: () -> Unit,
    onSaveClicked: () -> Unit
) {
    var paddingValues: PaddingValues
    var dateTime by remember { mutableStateOf(ZonedDateTime.now()) }
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM HH:mm")

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
                actions = {
                    Text(text = dateTime.format(dateTimeFormatter))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onSaveClicked() }) {
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
        var receiptUri by remember { mutableStateOf<Uri?>(null) }

        val pickImageAndCrop = rememberLauncherForActivityResult(
            CropImageContract()
        ) { result ->
            if (result.isSuccessful) {
                val uriContent = result.uriContent
                if (uriContent != null) {
                    receiptUri = uriContent
                }
            } else {
                val exception = result.error
                Timber.d("fun cropImage", "$exception")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagePickerAndHolder(receiptUri = receiptUri, pickImageAndCrop = pickImageAndCrop)
            GeneralReceiptInputsHolder(
                focusRequester = focusRequester,
                viewModel = viewModel,
                onDateTimeUpdated = {
                    dateTime = it
                })
        }
    }
}