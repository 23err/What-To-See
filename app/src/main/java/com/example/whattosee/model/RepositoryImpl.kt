package com.example.whattosee.model

import com.example.whattosee.toFilm
import com.example.whattosee.toFilms


object RepositoryImpl : Repository {
    var categories: List<Category>? = null

    private fun loadFilms() {
        val pageDTO = FilmsLoader.loadFilms()
        val films = pageDTO.toFilms()
        categories = listOf(
            Category(1, "Все", films),
            Category(2, "По рейтингу", films),
            Category(3, "Смотреть позже", films),
            Category(4, "Оцененные мной", films),
            Category(5, "Просмотренные", films),
        )
    }

    override fun getCategoryList(): List<Category>? {
        categories ?: loadFilms()
        return categories
    }

    override fun getCategory(id: Int): Category? = categories?.find { it.id == id }

    override fun getFilm(id: Int): Film {
        val filmDTO = FilmsLoader.loadFilm(id)
        return filmDTO.toFilm()
    }
}

