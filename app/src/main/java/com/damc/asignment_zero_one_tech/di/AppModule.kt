package com.damc.asignment_zero_one_tech.di

import android.content.Context
import com.damc.asignment_zero_one_tech.data.local.LocalRepositoryImpl
import com.damc.asignment_zero_one_tech.data.local.room.DatabaseClient
import com.damc.asignment_zero_one_tech.data.local.room.OnDataBaseActions
import com.damc.asignment_zero_one_tech.domain.LocalRepostories
import com.damc.asignment_zero_one_tech.ui.home.HomeViewModel
import com.damc.asignment_zero_one_tech.ui.home.allBooks.AllBooksViewModel
import com.damc.asignment_zero_one_tech.ui.home.completed.CompletedViewModel
import com.damc.asignment_zero_one_tech.ui.home.homeMian.HomeMianViewModel
import com.damc.asignment_zero_one_tech.ui.home.ongoing.OnGoingViewModel
import com.damc.asignment_zero_one_tech.ui.launcher.LauncherViewModel
import com.damc.asignment_zero_one_tech.ui.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {


    single<OnDataBaseActions> {
        DatabaseClient(get()).getAppDatabase()!!.OnDataBaseActions()
    }

    single<LocalRepostories> {
        LocalRepositoryImpl(get())
    }

    viewModel { LauncherViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { AllBooksViewModel() }
    viewModel { CompletedViewModel() }
    viewModel { HomeViewModel() }
    viewModel { OnGoingViewModel() }
    viewModel { HomeMianViewModel(get()) }
}