package com.damc.asignment_zero_one_tech.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import com.damc.asignment_zero_one_tech.domain.models.Users

@Dao
interface OnDataBaseActions {
    @Insert
    suspend fun insertUser(users: Users)
}