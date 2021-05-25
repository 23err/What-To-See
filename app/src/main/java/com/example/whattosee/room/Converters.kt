package com.example.whattosee.room

import androidx.room.TypeConverter
import java.sql.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?) : Date? {
        return value?.let {Date(it)}
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}