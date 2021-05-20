package com.example.whattosee.model

import retrofit2.Callback

interface Repository {
    fun getCategoryList(callback : Callback<PageDTO>)
    fun getCategory(id:Int): Category?
    fun getFilm(id: Int): Film?
}