package com.cleverexpenses.receipts.feature_receipt.presentation.util

sealed class Screen(val route: String){
    object ReceiptsScreen: Screen("receipts_screen")
    object AddEditScreen: Screen("add_edit_screen")
}
