package com.example.whattosee.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.sql.Date

@Dao
interface WatchHistoryDAO {
    @Query("SELECT * FROM WatchHistoryEntity")
    fun all(): List<WatchHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(entity: WatchHistoryEntity)
}