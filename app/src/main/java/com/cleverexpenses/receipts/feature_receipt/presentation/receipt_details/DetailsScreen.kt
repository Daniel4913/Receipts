package com.cleverexpenses.receipts.feature_receipt.presentation.receipt_details

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: ReceiptDetailsState,
    onBackPressed: () -> Unit,
    navigateToAddEditReceiptWithArgs: (Int) -> Unit,
) {
    var paddingValues: PaddingValues

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = state.receiptDisplayable.shopName) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Navigate back icon"
                        )
                    }
                }
            )
        }
    ) {
        paddingValues = it
        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
        ) {
            Text(text = "Receipt Details")
            Text(
                text = "${state.receiptId}"
            )
            Text(
                text = "${state.receiptDisplayable.shopName}"
            )
            if (state.receiptDisplayable.receiptPhotoUri != null) {

                LoadImageWithCoil(uri = state.receiptDisplayable.receiptPhotoUri)
            }
        }
    }
}

@Composable
fun LoadImageWithCoil(uri: Uri) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = uri).apply(block = fun ImageRequest.Builder.() {
            scale(Scale.FILL)
        }).build()
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}