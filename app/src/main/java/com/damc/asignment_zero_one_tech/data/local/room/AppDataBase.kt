package com.damc.asignment_zero_one_tech.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.damc.asignment_zero_one_tech.domain.models.BookStatus
import com.damc.asignment_zero_one_tech.domain.models.Books
import com.damc.asignment_zero_one_tech.domain.models.Users

@Database(
    entities = [Users::class, Books::class, BookStatus::class],
    version = 3,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun OnDataBaseActions(): OnDataBaseActions
}