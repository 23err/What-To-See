package com.example.whattosee.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(WatchHistoryEntity::class, CommentEntity::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WatchHistoryDatabase : RoomDatabase() {
    abstract fun watchHistoryDao(): WatchHistoryDAO
    abstract fun commentDao(): CommentDao
}