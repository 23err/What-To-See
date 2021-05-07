package com.example.whattosee.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.model.Repository
import com.example.whattosee.model.RepositoryImpl
import com.example.whattosee.model.datastate.FilmDataState
import java.lang.Error

class FilmViewModel(
    val liveDataToObserve: MutableLiveData<FilmDataState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {
    fun getFilm(id: Int) {
        liveDataToObserve.value = FilmDataState.Loading
        Thread {
            Thread.sleep(1000)
            val film = repository.getFilm(id)
            film?.apply {
                liveDataToObserve.postValue(FilmDataState.Success(film))
            } ?: liveDataToObserve.postValue(FilmDataState.Error(Error("Фильм с таким id не найден")))
        }.start()
    }
}