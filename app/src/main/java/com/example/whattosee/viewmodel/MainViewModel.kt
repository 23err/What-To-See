package com.example.whattosee.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.model.datastate.CategoriesDataState
import com.example.whattosee.model.Repository
import com.example.whattosee.model.RepositoryImpl

class MainViewModel(
    val liveDataToObserve: MutableLiveData<CategoriesDataState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    companion object {
        private const val DELAY = 1000L
    }

    fun getCategories() {
        liveDataToObserve.value = CategoriesDataState.Loading
        Thread() {
            Thread.sleep(DELAY)
            repository.getCategoryList()?.let {
                liveDataToObserve.postValue(CategoriesDataState.Success(it))
            } ?: liveDataToObserve.postValue(CategoriesDataState.Error(Error("Что-то пошло не так")))
        }.start()
    }
}