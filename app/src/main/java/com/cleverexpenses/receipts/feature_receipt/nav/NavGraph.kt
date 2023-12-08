package com.cleverexpenses.receipts.feature_receipt.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditScreen
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditViewModel
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.ReceiptsScreen
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.ReceiptsViewModel
import com.cleverexpenses.receipts.feature_receipt.presentation.util.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        receiptsRoute(
            navigateToAddReceipt = {
                navController.navigate(Screen.AddEditScreen.route + "?receiptId=-1")
            }
        )

        addReceiptRoute(
            navigateBack = {
                navController.popBackStack()
            },
            onSaveClicked = {
//                navController.popBackStack()
            }
        )
    }
}


fun NavGraphBuilder.receiptsRoute(
    navigateToAddReceipt: () -> Unit
) {
    composable(route = Screen.ReceiptsScreen.route) {
        val viewModel: ReceiptsViewModel = koinViewModel()
        ReceiptsScreen(viewModel = viewModel, navigateToAddReceipt = navigateToAddReceipt)
    }
}

fun NavGraphBuilder.addReceiptRoute(
    navigateBack: () -> Unit,
    onSaveClicked: () -> Unit
) {
    composable(route = Screen.AddEditScreen.route + "?receiptId={receiptId}",
        arguments = listOf(
            navArgument(name = "receiptId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        val viewModel: AddEditViewModel = koinViewModel()
        AddEditScreen(
            viewModel = viewModel,
            onBackPressed = { navigateBack() },
            onSaveClicked = { onSaveClicked() }
        )
    }
}