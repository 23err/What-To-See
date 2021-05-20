package com.example.whattosee.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.model.RemoteDataSource
import com.example.whattosee.model.Repository
import com.example.whattosee.model.RepositoryImpl
import com.example.whattosee.model.datastate.FilmDataState
import java.lang.Error

class FilmViewModel(
    val liveDataToObserve: MutableLiveData<FilmDataState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource())
) : ViewModel() {

    companion object{
        private const val DELAY = 1000L
    }

    fun getFilm(id: Int) {
        liveDataToObserve.value = FilmDataState.Loading
        Thread {
            Thread.sleep(DELAY)
            repository.getFilm(id)?.let{
                liveDataToObserve.postValue(FilmDataState.Success(it))
            } ?: liveDataToObserve.postValue(FilmDataState.Error(Error("Фильм с таким id не найден")))
        }.start()
    }
}