package com.example.whattosee.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.App
import com.example.whattosee.R
import com.example.whattosee.model.*
import com.example.whattosee.model.datastate.CategoriesDataState
import com.example.whattosee.toFilms
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    val liveDataToObserve: MutableLiveData<CategoriesDataState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource())
) : ViewModel() {

    companion object {
        private const val DELAY = 1000L
        private const val TAG = "MainViewModel"
    }

    private val callback = object : Callback<PageDTO>{
        override fun onResponse(call: Call<PageDTO>, response: Response<PageDTO>) {
            if (response.isSuccessful){
                response.body()?.let {
                    val films = it.toFilms()
                    val categories = listOf(
                        Category(1, "Все", films),
                        Category(2, "По рейтингу", films),
                        Category(3, "Смотреть позже", films),
                        Category(4, "Оцененные мной", films),
                        Category(5, "Просмотренные", films),
                    )
                    liveDataToObserve.postValue(CategoriesDataState.Success(categories))
                } ?: run {
                    liveDataToObserve.postValue(CategoriesDataState.Error(
                        Error(App.context.resources.getString(R.string.error_occurred))
                    ))
                }
            } else {
                var message = ""
                response.errorBody()?.let {
                    message = it.string()
                } ?: run{
                    message = App.context.getString(R.string.unknown_error)
                }
                Log.e(TAG, message)
                liveDataToObserve.postValue(CategoriesDataState.Error(
                    Error(App.context.resources.getString(R.string.error_occurred))
                ))
            }

        }

        override fun onFailure(call: Call<PageDTO>, t: Throwable) {
            Log.e(TAG, t.stackTraceToString())
            liveDataToObserve.postValue(CategoriesDataState.Error(
                Error(App.context.resources.getString(R.string.error_occurred))
            ))
        }
    }

    fun getCategories() {
        liveDataToObserve.value = CategoriesDataState.Loading
        repository.getCategoryList(callback)
//        Thread() {
//            try {
//                repository.getCategoryList()?.let {
//                    liveDataToObserve.postValue(CategoriesDataState.Success(it))
//                } ?: liveDataToObserve.postValue(CategoriesDataState.Error(Error("Что-то пошло не так")))
//            } catch (e : Throwable){
//                liveDataToObserve.postValue(CategoriesDataState.Error(Error("Произошла ошибки во время загрузки")))
//                Log.e(TAG, e.message.toString())
//            }
//
//        }.start()
    }


}