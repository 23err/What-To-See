package com.example.whattosee.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.util.*

@Entity
data class WatchHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val film_id: Long,
    val date: Date = Date(Calendar.getInstance().time.time)
)
