package com.cleverexpenses.receipts.feature_receipt.domain.use_case

data class ReceiptUseCases(
    val getAllReceiptsWithProducts: GetAllReceiptsWithProducts,
    val getReceiptWithProducts: GetReceiptWithProducts,
    val deleteReceipt: DeleteReceipt,
    val deleteProduct: DeleteProduct,
    val addReceipt: AddReceipt,
    val addProducts: AddProduct,
)