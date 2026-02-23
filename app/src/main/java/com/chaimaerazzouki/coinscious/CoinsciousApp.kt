package com.chaimaerazzouki.coinscious

import android.app.Application
import com.chaimaerazzouki.coinscious.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

// Root Compose app with navigation
class CoinsciousApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CoinsciousApp)
            modules(appModule)
        }
    }

}