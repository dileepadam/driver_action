package com.damc.asignment_zero_one_tech.data.local

import com.damc.asignment_zero_one_tech.data.local.room.OnDataBaseActions
import com.damc.asignment_zero_one_tech.domain.LocalRepostories
import com.damc.asignment_zero_one_tech.domain.models.Users

class LocalRepositoryImpl(val dataBase: OnDataBaseActions) : LocalRepostories {

    override suspend fun insertUser(users: Users) {
        dataBase.insertUser(users)
    }
}