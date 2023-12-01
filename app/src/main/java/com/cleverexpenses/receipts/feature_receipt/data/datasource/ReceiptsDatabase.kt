package com.cleverexpenses.receipts.feature_receipt.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt

@Database(
    entities = [
        Receipt::class,
        Product::class],
    version = 1
)
abstract class ReceiptsDatabase : RoomDatabase() {
    abstract val receiptDao: ReceiptDao

    companion object {
        const val DATABASE_NAME = "receipt_db"
    }
}