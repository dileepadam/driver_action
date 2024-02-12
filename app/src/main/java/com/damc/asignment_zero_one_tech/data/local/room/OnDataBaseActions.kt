package com.damc.asignment_zero_one_tech.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.damc.asignment_zero_one_tech.domain.models.Books
import com.damc.asignment_zero_one_tech.domain.models.Users

@Dao
interface OnDataBaseActions {
    @Insert
    suspend fun insertUser(users: Users)

    @Query("SELECT COUNT(*) FROM users WHERE username LIKE :username LIMIT 1")
    fun isUsernameInDb(username: String): Int

    @Query("SELECT * FROM users WHERE username LIKE :username AND password LIKE :password LIMIT 1")
    suspend fun userLogin(username: String, password: String): Users

    @Query("SELECT COUNT(*) FROM books LEFT JOIN book_status " +
            "ON books.book_id = book_status.bookId WHERE book_status.mark_favorite = true AND book_status.userId = :user_Id")
    suspend fun getFavouriteBookCount(user_Id: Int): Int

    @Insert
    suspend fun insertBook(books: Books)
}