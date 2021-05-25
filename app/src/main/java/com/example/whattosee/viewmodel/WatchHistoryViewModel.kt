package com.example.whattosee.viewmodel

import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.MutableLiveData
import com.example.whattosee.app.App
import com.example.whattosee.model.datastate.CategoryDataState
import com.example.whattosee.repository.LocalRepository
import com.example.whattosee.repository.LocalRepositoryImpl

class WatchHistoryViewModel(
    val liveData: MutableLiveData<CategoryDataState>,
    private val watchHistoryRepo : LocalRepository = LocalRepositoryImpl(App.getWatchHistoryDao(),)
) {
    fun getAll() {
        liveData.value = CategoryDataState.Loading

        App.handler.post{
            val watchHistories = watchHistoryRepo.getAllWatchHistory()
        }

    }
}