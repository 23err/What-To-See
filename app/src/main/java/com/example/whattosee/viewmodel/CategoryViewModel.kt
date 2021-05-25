package com.example.whattosee.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.app.App
import com.example.whattosee.R
import com.example.whattosee.model.*
import com.example.whattosee.model.datastate.CategoryDataState
import com.example.whattosee.toFilms
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel(
    val liveData: MutableLiveData<CategoryDataState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource())
) : ViewModel() {

    companion object {
        private const val TAG = "CategoryViewModel"
    }

    var categoryId: Int = 0

    fun getFilms(idCategory: Int) {
        categoryId = idCategory
        liveData.value = CategoryDataState.Loading
        repository.getCategory(idCategory, callback)
    }

    private fun liveDataError() {
        liveData.postValue(
            CategoryDataState.Error(
                Error(App.context.resources.getString(R.string.error_occurred))
            )
        )
    }

    val callback = object : Callback<PageDTO> {
        override fun onResponse(call: Call<PageDTO>, response: Response<PageDTO>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    val films = it.toFilms()
                    val categories = listOf(
                        Category(1, "Все", films),
                        Category(2, "По рейтингу", films),
                        Category(3, "Смотреть позже", films),
                        Category(4, "Оцененные мной", films),
                        Category(5, "Просмотренные", films),
                    )
                    val category = categories.find { it.id == categoryId }
                    category?.let {
                        liveData.postValue(CategoryDataState.Success(category))
                    }
                } ?: run {
                    liveDataError()
                }
            } else {
                var message = ""
                response.errorBody()?.let {
                    message = it.string()
                } ?: run {
                    message = App.context.getString(R.string.unknown_error)
                }
                Log.e(TAG, message)
                liveDataError()
            }
        }

        override fun onFailure(call: Call<PageDTO>, t: Throwable) {
            Log.e(TAG, t.stackTraceToString())
            liveDataError()
        }
    }
}