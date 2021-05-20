package com.example.whattosee.model

import com.example.whattosee.repository.FilmAPI
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val filmsApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build()
        .create(FilmAPI::class.java)


    fun getFilms(callback: Callback<PageDTO>){
        filmsApi.getFilms().enqueue(callback)
    }

    fun getFilm(filmId:Int, callback: Callback<FilmDTO>){
        filmsApi.getFilm(filmId).enqueue(callback)
    }

    fun getCategory(category_id: Int, callback: Callback<PageDTO>){
        getFilms(callback)
    }
}