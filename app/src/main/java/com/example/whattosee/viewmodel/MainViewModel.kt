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

    fun getCategories() {
        liveDataToObserve.value = CategoriesDataState.Loading
        Thread() {
            Thread.sleep(1000)
            var categories = repository.getCategoryList()
            if (categories != null) {
                liveDataToObserve.postValue(CategoriesDataState.Success(categories))
            } else {
                liveDataToObserve.postValue(CategoriesDataState.Error(Error("Что-то пошло не так")))
            }

        }.start()

    }

}