package com.example.whattosee.model
interface Repository {
    fun getCategoryList(): List<Category>?
    fun getCategory(id:Int): Category?
    fun getFilm(id: Int): Film?
}