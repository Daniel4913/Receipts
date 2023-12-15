package com.cleverexpenses.receipts.feature_receipt.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Receipt(
    @PrimaryKey val receiptId: Int? = null,
    val shopName: String,
    val shopAddress: String,
    val receiptDate: Long,
    val createDate: Long,
    val sum: Int,
    val paymentMethod: String,
    val currency: String,
    val receiptPhotoUri: String? = null
)