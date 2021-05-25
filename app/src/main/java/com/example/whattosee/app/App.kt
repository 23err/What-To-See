package com.example.whattosee.app

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.room.Room
import com.example.whattosee.room.CommentDao
import com.example.whattosee.room.WatchHistoryDAO
import com.example.whattosee.room.WatchHistoryDatabase
import java.lang.IllegalStateException

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        appInstance = this
    }


    companion object {
        lateinit var context: Context
        private var appInstance: App? = null
        private var db: WatchHistoryDatabase? = null
        private const val DB_NAME = "WatchHistory.db"
        private const val APPLICATION_IS_NULL = "Application is null while creating Database"
        private const val HANDLER_NAME = "handler Thread"
        private val handlerThread: HandlerThread by lazy { HandlerThread(HANDLER_NAME) }
        val handler:Handler by lazy {
            handlerThread.start()
            Handler(handlerThread.looper)
        }


        fun getWatchHistoryDao(): WatchHistoryDAO {
            getDB()
            return db!!.watchHistoryDao()
        }

        fun getCommentDao(): CommentDao {
            getDB()
            return db!!.commentDao()
        }

        private fun getDB() {
            if (db == null) {
                synchronized(WatchHistoryDatabase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException(APPLICATION_IS_NULL)
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            WatchHistoryDatabase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        handlerThread.quitSafely()
    }

}