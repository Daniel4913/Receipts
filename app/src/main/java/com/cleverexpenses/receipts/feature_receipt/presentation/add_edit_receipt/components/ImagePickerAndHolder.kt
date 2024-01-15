package com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.components

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ImagePickerAndHolder(
    receiptUri: Uri? = null,
    pickImageAndCrop: ManagedActivityResultLauncher<CropImageContractOptions, CropImageView.CropResult>,
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
                        CropImageContractOptions(uri = receiptUri, cropImageOptions = cropOptions)
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
}