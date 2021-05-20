package com.example.whattosee.repository

import com.example.whattosee.BuildConfig
import com.example.whattosee.model.PageDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmAPI {
    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") apiKey:String = BuildConfig.FILMS_API_KEY
    ): Call<PageDTO>
}