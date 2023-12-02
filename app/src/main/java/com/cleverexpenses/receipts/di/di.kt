package com.cleverexpenses.receipts.di

import androidx.room.Room
import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptsDatabase
import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptsDatabase.Companion.DATABASE_NAME
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            get(),
            ReceiptsDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<ReceiptsDatabase>().receiptDao
    }
}


