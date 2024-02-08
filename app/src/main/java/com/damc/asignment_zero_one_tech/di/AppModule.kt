package com.damc.asignment_zero_one_tech.di

import android.content.Context
import com.damc.asignment_zero_one_tech.data.local.LocalRepositoryImpl
import com.damc.asignment_zero_one_tech.data.local.room.AppDataBase
import com.damc.asignment_zero_one_tech.data.local.room.DatabaseClient
import com.damc.asignment_zero_one_tech.data.local.room.OnDataBaseActions
import com.damc.asignment_zero_one_tech.domain.LocalRepostories
import com.damc.asignment_zero_one_tech.ui.launcher.LauncherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module{

    single<Context> {
        androidContext()
    }

    single<OnDataBaseActions> {
        DatabaseClient(get()).getInstance()?.getAppDatabase()!!.OnDataBaseActions()
    }

    single<LocalRepostories> {
        LocalRepositoryImpl(get())
    }

    viewModel { LauncherViewModel() }
}