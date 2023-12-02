package com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptWithProducts
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.components.ReceiptItem
import io.github.serpro69.kfaker.Faker
import kotlin.random.Random

val random = Random(3)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptsScreen(
//    viewModel: ReceiptsViewModel
) {
    var paddingValues: PaddingValues


    val faker = Faker()
    val receipts = List(10) {
        ReceiptWithProducts(
            receipt = Receipt(
                receiptId = random.nextInt(),
                shopName = faker.company.name(),
                date = "12 Nov 12:33",
                sum = random.nextInt(30, 1024),
                paymentMethod = generateRandomPayment(random)
            ),
            product = List(size = random.nextInt(1, 20)) {
                Product(
                    productId = random.nextInt(),
                    name = faker.commerce.productName(),
                    quantity = random.nextInt(1, 20),
                    unitPrice = random.nextInt(1, 1000),
                    totalPrice = random.nextInt(1, 20000),
                    receiptOwnerId = 0
                )
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Receipt")
            }
        }
    ) {
        paddingValues = it
        LazyColumn(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
        ) {
            items(receipts) { receipt ->
                ReceiptItem(
                    receipt = receipt,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

fun generateRandomPayment(random: Random): String {
    return if (random.nextInt() < 200) {
        "Cash"
    } else "Card"
}

