package com.cleverexpenses.receipts.di

import androidx.room.Room
import com.cleverexpenses.receipts.feature_receipt.data.ReceiptRepositoryImpl
import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptDao
import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptsDatabase
import com.cleverexpenses.receipts.feature_receipt.data.datasource.ReceiptsDatabase.Companion.DATABASE_NAME
import com.cleverexpenses.receipts.feature_receipt.data.repository.ReceiptRepository
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.AddProduct
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.AddReceipt
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.DeleteProduct
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.DeleteReceipt
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.GetAllReceiptsWithProducts
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.GetReceiptWithProducts
import com.cleverexpenses.receipts.feature_receipt.domain.use_case.ReceiptUseCases
import com.cleverexpenses.receipts.feature_receipt.presentation.add_edit_receipt.AddEditViewModel
import com.cleverexpenses.receipts.feature_receipt.presentation.receipts_list.ReceiptsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

    single<ReceiptDao> {
        get<ReceiptsDatabase>().receiptDao
    }

    single<ReceiptRepository> {
        ReceiptRepositoryImpl(
            receiptDao = get()
        )
    }

    single {
        GetAllReceiptsWithProducts(
            repository = get()
        )
    }
    single {
        GetReceiptWithProducts(
            repository = get()
        )
    }
    single {
        DeleteReceipt(
            repository = get()
        )
    }
    single {
        DeleteProduct(
            repository = get()
        )
    }

    single {
        AddReceipt(
            repository = get()
        )
    }

    single {
        AddProduct(
            repository = get()
        )
    }

    single {
        ReceiptUseCases(
            getAllReceiptsWithProducts = get(),
            getReceiptWithProducts = get(),
            deleteReceipt = get(),
            deleteProduct = get(),
            addReceipt = get(),
            addProducts = get()
        )
    }

    viewModel {
        AddEditViewModel(
            useCases = get()
        )
    }

    viewModel {
        ReceiptsViewModel(
            receiptUseCases = get()
        )
    }
}