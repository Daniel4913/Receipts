package com.cleverexpenses.receipts

import android.app.Application
import com.cleverexpenses.receipts.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.Forest.plant

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        plant(Timber.DebugTree())

        startKoin{
            androidContext(this@App)
            modules(appModule)
        }
    }
}