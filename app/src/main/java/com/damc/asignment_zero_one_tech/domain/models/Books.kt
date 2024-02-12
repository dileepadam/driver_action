package com.damc.asignment_zero_one_tech.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Books(
    @ColumnInfo(name = "user_Id")
    var user_Id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "publisher")
    var publisher: String,
    @ColumnInfo(name = "edition")
    var edition: String,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "language")
    var language: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    var bookId: Int = 0
}
