package com.cleverexpenses.receipts.feature_receipt.domain.use_case

data class ReceiptUseCases(
    val getReceipts: GetReceipts,
    val getReceiptsWithProducts: GetReceiptWithProducts,
    val deleteReceipt: DeleteReceipt,
    val deleteProduct: DeleteProduct,
    val addReceipt: AddReceipt,
    val addProducts: AddProduct,
)