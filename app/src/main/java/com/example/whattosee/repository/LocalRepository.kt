package com.example.whattosee.repository

import com.example.whattosee.room.CommentEntity
import com.example.whattosee.room.WatchHistoryEntity

interface LocalRepository {
    fun getAllWatchHistory(): List<WatchHistoryEntity>
    fun save(watchHistoryEntity: WatchHistoryEntity)
    fun saveComment(commentEntity: CommentEntity)
    fun getComments(filmId:Long):List<CommentEntity>
}