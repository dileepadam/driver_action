package com.damc.asignment_zero_one_tech.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_status")
data class BookStatus(
    @ColumnInfo(name = "userId")
    var userID: Int,
    @ColumnInfo(name = "bookId")
    var bookId: Int,
    @ColumnInfo(name = "status")
    var status: String,
    @ColumnInfo(name = "mark_favorite")
    var markAsFavorite: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "status_id")
    var statusId: Int = 0
}
