package com.damc.asignment_zero_one_tech.app

import android.app.Application
import com.damc.asignment_zero_one_tech.di.appModule
import com.damc.asignment_zero_one_tech.domain.models.Users
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AssignmentApplication : Application() {

    var user: Users? = null

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AssignmentApplication)
            modules(appModule)
        }
    }

    fun setLoginUser(users: Users) {
        user = users
    }

    fun getLoginUser(): Users {
        return user!!
    }


}