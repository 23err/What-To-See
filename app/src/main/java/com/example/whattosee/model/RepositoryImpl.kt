package com.example.whattosee.model

import retrofit2.Callback


class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override fun getCategoryList(callback: Callback<PageDTO>){
        remoteDataSource.getFilms(callback)
    }

    override fun getCategory(id: Int, callback: Callback<PageDTO>){
        remoteDataSource.getCategory(id, callback)
    }

    override fun getFilm(id: Int, callback: Callback<FilmDTO>){
        remoteDataSource.getFilm(id, callback)
    }
}

