package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt

import android.graphics.Color
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.cleverexpenses.receipts.R
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.components.GeneralReceiptInputsHolder
import timber.log.Timber

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

        //
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

        //
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .matchParentSize()
                    ) {
                        if (receiptUri != null) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentDescription = "Receipt async image from uri",
                                model = receiptUri,
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            val cropOptions = CropImageOptions(
                                guidelinesColor = Color.BLACK,
                                borderLineColor = Color.BLACK,
                                borderCornerColor = Color.RED,
                            )
                            val contractOptions =
                                CropImageContractOptions(uri = null, cropImageOptions = cropOptions)
                            pickImageAndCrop.launch(contractOptions)
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Send,
                            contentDescription = "Add receipt image button",
                            tint = if (receiptUri == null) {
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            } else {
                                MaterialTheme.colorScheme.primary
                            }
                        )
                    }
                }
            }

            GeneralReceiptInputsHolder(focusRequester = focusRequester, viewModel = viewModel)
        }
    }
}
