package com.example.whattosee.model

import com.google.gson.Gson

object FilmsLoader {

    private const val FILMS_URL = "https://api.themoviedb.org/3/movie/popular?language=ru&page=1"
    private const val FILM_URL = "https://api.themoviedb.org/3/movie/{film_id}?language=ru"

    fun loadFilms():PageDTO{
        val body = UrlLoader().load(FILMS_URL)
        val pageDTO = Gson().fromJson(body, PageDTO::class.java)
        return pageDTO
    }

    fun loadFilm(idFilm: Int): FilmDTO {
        val body = UrlLoader().load(FILM_URL.replace("{film_id}", idFilm.toString()))
        val filmDTO = Gson().fromJson(body, FilmDTO::class.java)
        return filmDTO
    }
}