package com.example.whattosee.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.util.*

@Entity
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val message:String,
    val filmId: Long,
    val date: Date = Date(Calendar.getInstance().time.time)
)