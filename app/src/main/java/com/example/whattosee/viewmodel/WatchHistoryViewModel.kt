package com.example.whattosee.viewmodel

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
        val watchHistories = watchHistoryRepo.getAllWatchHistory()

//        liveData.value = CategoryDataState.Success(watchHistoryRepo.getAllWatchHistory())
    }
}