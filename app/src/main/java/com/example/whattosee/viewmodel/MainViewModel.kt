package com.example.whattosee.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.model.datastate.CategoriesDataState
import com.example.whattosee.model.Repository
import com.example.whattosee.model.RepositoryImpl

class MainViewModel(
    val liveDataToObserve: MutableLiveData<CategoriesDataState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl
) : ViewModel() {

    companion object {
        private const val DELAY = 1000L
        private const val TAG = "MainViewModel"
    }

    fun getCategories() {
        liveDataToObserve.value = CategoriesDataState.Loading
        Thread() {
            try {
                repository.getCategoryList()?.let {
                    liveDataToObserve.postValue(CategoriesDataState.Success(it))
                } ?: liveDataToObserve.postValue(CategoriesDataState.Error(Error("Что-то пошло не так")))
            } catch (e : Throwable){
                liveDataToObserve.postValue(CategoriesDataState.Error(Error("Произошла ошибки во время загрузки")))
                Log.e(TAG, e.message.toString())
            }

        }.start()
    }
}