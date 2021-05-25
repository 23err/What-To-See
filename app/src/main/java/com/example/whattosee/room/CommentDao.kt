package com.example.whattosee.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CommentDao {
    @Query("SELECT * FROM CommentEntity WHERE filmId = :filmId")
    fun getCommentsToFilm(filmId:Long):List<CommentEntity>

    @Insert
    fun saveComment(commentEntity: CommentEntity)
}