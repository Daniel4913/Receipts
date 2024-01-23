package com.cleverexpenses.receipts.feature_receipt.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditReceiptEvent
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditScreen
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditViewModel
import com.cleverexpenses.receipts.feature_receipt.presentation.receipt_details.DetailsScreen
import com.cleverexpenses.receipts.feature_receipt.presentation.receipt_details.DetailsViewModel
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.ReceiptsScreen
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.ReceiptsViewModel
import com.cleverexpenses.receipts.feature_receipt.presentation.util.Constants.RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY
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
            },
            navigateToReceiptDetails = { receiptId ->
                navController.navigate(Screen.ReceiptDetailsScreen.passReceiptId(receiptId))
            }
        )

        addReceiptRoute(
            navigateBack = {
                navController.popBackStack()
            },
        )

        receiptDetailsRoute(
            navigateBack = {
                navController.popBackStack()
            },
            navigateToEditReceipt = { receiptId ->
                navController.navigate(Screen.AddEditScreen.passReceiptId(receiptId))
            }
        )
    }
}


fun NavGraphBuilder.receiptsRoute(
    navigateToAddReceipt: () -> Unit,
    navigateToReceiptDetails: (Int) -> Unit
) {
    composable(route = Screen.ReceiptsScreen.route) {
        val viewModel: ReceiptsViewModel = koinViewModel()
        ReceiptsScreen(
            viewModel = viewModel,
            navigateToAddReceipt = navigateToAddReceipt,
            onReceiptClicked = { receiptId ->
                navigateToReceiptDetails(receiptId)
            }
        )
    }
}

fun NavGraphBuilder.addReceiptRoute(
    navigateBack: () -> Unit,

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
            onSaveClicked = {
                viewModel.onEvent(AddEditReceiptEvent.SaveReceipt)
            }
        )
        LaunchedEffect(key1 = viewModel.state.value.receiptSaved) {
            if (viewModel.state.value.receiptSaved) {
                navigateBack()
            }
        }
    }
}

fun NavGraphBuilder.receiptDetailsRoute(
    navigateBack: () -> Unit,
    navigateToEditReceipt: (Int) -> Unit
) {
    composable(
        route = Screen.ReceiptDetailsScreen.route,
        arguments = listOf(
            navArgument(
                name = RECEIPT_DETAILS_SCREEN_ARGUMENT_KEY
            ) {
                type = NavType.IntType
            }
        )
    ) {
        val viewModel: DetailsViewModel = koinViewModel()
        DetailsScreen(
            state = viewModel.state,
            onBackPressed = navigateBack,
            navigateToAddEditReceiptWithArgs = { receiptId ->
                navigateToEditReceipt(receiptId)
            },
        )
    }
}