package com.cleverexpenses.receipts.feature_receipt.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cleverexpenses.receipts.feature_receipt.domain.model.Product
import com.cleverexpenses.receipts.feature_receipt.domain.model.Receipt
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM Receipt")
    fun getAllReceipts(): Flow<List<Receipt>>

    @Transaction
    @Query("SELECT * FROM Receipt WHERE receiptId = :receiptId")
    suspend fun getReceiptWithProducts(receiptId: Int): ReceiptWithProducts

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceipt(receipt: Receipt)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteReceipt(receipt: Receipt)

    @Delete
    suspend fun deleteProduct(product: Product)

}