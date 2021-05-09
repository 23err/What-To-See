package com.example.whattosee.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.model.datastate.CategoryDataState
import com.example.whattosee.model.Repository
import com.example.whattosee.model.RepositoryImpl

class CategoryViewModel(
    val liveData: MutableLiveData<CategoryDataState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    companion object{
        private const val DELAY = 1000L
    }

    fun getFilms(idCategory: Int) {
        liveData.value = CategoryDataState.Loading
        Thread{
            Thread.sleep(DELAY)
            val category = repository.getCategory(idCategory)
            if (category == null) {
                liveData.postValue(CategoryDataState.Error(Error("Нет категорий с таким id")))
            } else {
                liveData.postValue(CategoryDataState.Success(category))
            }
        }.start()

    }
}