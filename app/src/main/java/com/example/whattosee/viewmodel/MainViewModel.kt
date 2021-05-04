package com.example.whattosee.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.model.Category
import com.example.whattosee.model.Repository
import com.example.whattosee.model.RepositoryImpl

class MainViewModel(
        val liveDataToObserve: MutableLiveData<List<Category>> = MutableLiveData(),
        private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    fun getCategories() {
        Thread(){
            Thread.sleep(1000)
            val categories = repository.getCategoryList()
            liveDataToObserve.postValue(categories)
        }.start()

    }

}