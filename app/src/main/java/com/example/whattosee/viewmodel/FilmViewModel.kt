package com.example.whattosee.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whattosee.app.App
import com.example.whattosee.R
import com.example.whattosee.model.FilmDTO
import com.example.whattosee.model.RemoteDataSource
import com.example.whattosee.model.Repository
import com.example.whattosee.model.RepositoryImpl
import com.example.whattosee.model.datastate.FilmDataState
import com.example.whattosee.repository.LocalRepository
import com.example.whattosee.repository.LocalRepositoryImpl
import com.example.whattosee.room.CommentEntity
import com.example.whattosee.room.WatchHistoryEntity
import com.example.whattosee.toFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error

class FilmViewModel(
    val liveDataToObserve: MutableLiveData<FilmDataState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource()),
    private val localRepo: LocalRepository = LocalRepositoryImpl(App.getWatchHistoryDao(), App.getCommentDao())
) : ViewModel() {

    companion object {
        private const val TAG = "FilmViewModel"
        private const val NO_DATA_FILM = "no data films"
    }

    fun getFilm(filmId: Int) {
        liveDataToObserve.value = FilmDataState.Loading
        repository.getFilm(filmId, callback)
    }

    fun saveWatchHistory(watchHistoryEntity: WatchHistoryEntity){
        App.handler.post{
            localRepo.save(watchHistoryEntity)
        }
    }

    fun saveComment(commentEntity: CommentEntity) {
        localRepo.saveComment(commentEntity)
    }

    fun getComments(filmId: Int, onGetComments: (list:List<CommentEntity>)->Unit){
        localRepo.getComments(filmId.toLong(), onGetComments)
    }

    private fun liveDataError(message: String = App.context.getString(R.string.error_occurred)) {
        liveDataToObserve.postValue(
            FilmDataState.Error(
                Error(message)
            )
        )
    }

    val callback = object : Callback<FilmDTO> {
        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    liveDataToObserve.postValue(FilmDataState.Success(it.toFilm()))
                } ?: run {
                    Log.e(TAG, NO_DATA_FILM)
                    response.errorBody()?.let {
                        Log.e(TAG, it.string())
                    }
                    liveDataError()
                }
            } else {
                liveDataError(App.context.getString(R.string.unknown_error))
            }
        }
        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            liveDataError()
            Log.e(TAG, t.stackTraceToString())
        }
    }


}