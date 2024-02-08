package com.damc.asignment_zero_one_tech.data.local.room

import android.content.Context
import androidx.room.Room.databaseBuilder

class DatabaseClient(private var mCtx: Context?) {

    private var mInstance: DatabaseClient? = null

    private var appDatabase: AppDataBase? = null

    init {
        appDatabase = mCtx?.let {
            databaseBuilder(it, AppDataBase::class.java, "zero_one_tech.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }



    @Synchronized
    fun getInstance(): DatabaseClient? {
        if (mInstance == null) {
            mInstance = DatabaseClient(mCtx)
        }
        return mInstance
    }

    fun getAppDatabase(): AppDataBase? {
        return appDatabase
    }
}