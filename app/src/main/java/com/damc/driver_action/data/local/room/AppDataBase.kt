package com.damc.driver_action.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.damc.driver_action.domain.models.ActionData
import com.damc.driver_action.domain.models.Users

@Database(
    entities = [Users::class, ActionData::class],
    version = 3,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun OnDataBaseActions(): OnDataBaseActions
}