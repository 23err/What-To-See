package com.example.whattosee.repository

import com.example.whattosee.BuildConfig
import com.example.whattosee.model.FilmDTO
import com.example.whattosee.model.PageDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmAPI {
    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") apiKey:String = BuildConfig.FILMS_API_KEY,
        @Query("language") language:String = "ru"
    ): Call<PageDTO>

    @GET("3/movie/{film_id}")
    fun getFilm(
        @Path("film_id", encoded = false) film_id:Int,
        @Query("api_key") apiKey:String = BuildConfig.FILMS_API_KEY,
        @Query("language") language:String = "ru"
    ): Call<FilmDTO>
}