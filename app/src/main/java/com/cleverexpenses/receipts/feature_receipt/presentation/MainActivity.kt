package com.cleverexpenses.receipts.feature_receipt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.ReceiptsScreen
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.ReceiptsViewModel
import com.cleverexpenses.receipts.ui.theme.ReceiptsTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReceiptsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: ReceiptsViewModel = koinViewModel()
                    ReceiptsScreen(viewModel = viewModel)
                }
            }
        }
    }
}
