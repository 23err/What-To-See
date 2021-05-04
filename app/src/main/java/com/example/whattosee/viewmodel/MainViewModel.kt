package com.example.whattosee.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.CategoryDataState
import com.example.whattosee.model.Category
import com.example.whattosee.model.Repository
import com.example.whattosee.model.RepositoryImpl

class MainViewModel(
        val liveDataToObserve: MutableLiveData<CategoryDataState> = MutableLiveData(),
        private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    fun getCategories() {
        liveDataToObserve.value = CategoryDataState.Loading
        Thread() {
            Thread.sleep(1000)
            var categories = repository.getCategoryList()
            if (categories != null) {
                liveDataToObserve.postValue(CategoryDataState.Success(categories))
            } else {
                liveDataToObserve.postValue(CategoryDataState.Error(Error("Что-то пошло не так")))
            }

        }.start()

    }

}