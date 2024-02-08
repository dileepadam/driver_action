package com.damc.asignment_zero_one_tech.app

import android.app.Application
import com.damc.asignment_zero_one_tech.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AssignmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AssignmentApplication)
            modules(appModule)
        }
    }
}