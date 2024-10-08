package com.farkasatesz.myshoppinglist

import android.app.Application
import com.farkasatesz.myshoppinglist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShoppingListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
            androidContext(this@ShoppingListApplication)
        }
    }
}