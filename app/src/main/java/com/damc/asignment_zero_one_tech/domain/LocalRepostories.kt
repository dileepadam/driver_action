package com.damc.asignment_zero_one_tech.domain

import com.damc.asignment_zero_one_tech.domain.models.Users

interface LocalRepostories {
    suspend fun insertUser(users: Users)
}