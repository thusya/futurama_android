package com.thus.futurama

import android.app.Application
import com.thus.futurama.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }
}