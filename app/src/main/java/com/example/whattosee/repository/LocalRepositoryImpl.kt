package com.example.whattosee.repository

import com.example.whattosee.app.App
import com.example.whattosee.room.CommentDao
import com.example.whattosee.room.CommentEntity
import com.example.whattosee.room.WatchHistoryDAO
import com.example.whattosee.room.WatchHistoryEntity

class LocalRepositoryImpl(
    private val watchHistoryDAO: WatchHistoryDAO = App.getWatchHistoryDao(),
    private val commentDao: CommentDao = App.getCommentDao()
    ): LocalRepository {
    override fun getAllWatchHistory(): List<WatchHistoryEntity> {
        return watchHistoryDAO.all()
    }

    override fun save(watchHistoryEntity: WatchHistoryEntity) {
        watchHistoryDAO.save(watchHistoryEntity)
    }

    override fun saveComment(commentEntity: CommentEntity) {
        commentDao.saveComment(commentEntity)
    }

    override fun getComments(filmId: Long) : List<CommentEntity>{
        return commentDao.getCommentToFilm(filmId)
    }
}