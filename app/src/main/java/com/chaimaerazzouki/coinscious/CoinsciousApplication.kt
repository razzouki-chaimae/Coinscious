package com.chaimaerazzouki.coinscious

import android.app.Application
import com.chaimaerazzouki.coinscious.di.coinsciousModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoinsciousApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CoinsciousApplication)
            modules(coinsciousModules)
        }
    }
}