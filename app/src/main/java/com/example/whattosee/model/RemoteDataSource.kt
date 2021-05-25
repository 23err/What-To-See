package com.example.whattosee.model

import com.example.whattosee.repository.FilmAPI
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
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
        .client(createOkHttpClient(FilmApiInterceptor()))
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

    private fun createOkHttpClient(interceptor: Interceptor):OkHttpClient{
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return httpClient.build()

    }

    inner class FilmApiInterceptor: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}