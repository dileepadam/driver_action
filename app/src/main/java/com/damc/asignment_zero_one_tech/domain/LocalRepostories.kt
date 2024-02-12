package com.damc.asignment_zero_one_tech.domain

import com.damc.asignment_zero_one_tech.domain.models.Books
import com.damc.asignment_zero_one_tech.domain.models.Users

interface LocalRepostories {
    suspend fun insertUser(users: Users)

    suspend fun isUsernameInDb(username:String):Int

    suspend fun userLogin(username: String, password: String): Users?

    suspend fun getFavouriteBookCount(user_Id: Int): Int
    suspend fun insertBook(books: Books)


}