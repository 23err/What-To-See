package com.example.whattosee.model.datastate

import com.example.whattosee.model.Film

sealed class FilmDataState {
    data class Success(val film: Film): FilmDataState()
    data class Error(val error: kotlin.Error): FilmDataState()
    object Loading: FilmDataState()
}
