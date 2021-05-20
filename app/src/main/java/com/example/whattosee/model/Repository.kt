package com.example.whattosee.model

import retrofit2.Callback

interface Repository {
    fun getCategoryList(callback : Callback<PageDTO>)
    fun getCategory(id:Int, callback: Callback<PageDTO>)
    fun getFilm(id: Int, callback: Callback<FilmDTO>)
}