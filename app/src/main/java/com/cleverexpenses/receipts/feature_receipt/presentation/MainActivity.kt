package com.cleverexpenses.receipts.feature_receipt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cleverexpenses.receipts.feature_receipt.nav.SetupNavGraph
import com.cleverexpenses.receipts.feature_receipt.presentation.util.Screen
import com.cleverexpenses.receipts.ui.theme.ReceiptsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReceiptsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SetupNavGraph(
                        startDestination = Screen.ReceiptsScreen.route,
                        navController = navController
                    )
                }
            }
        }
    }
}
