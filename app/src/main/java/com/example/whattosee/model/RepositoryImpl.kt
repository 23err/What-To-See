package com.example.whattosee.model

import com.example.whattosee.toFilms

object RepositoryImpl : Repository {
    var categories: List<Category>? = null

    private fun loadFilms() {
        val filmsDTO = FilmLoader().loadFilms()
        val films = filmsDTO.toFilms()
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

    override fun getFilm(id: Int): Film? {
        var film: Film? = null
        categories?.find {
            film = it.films.find { filmItem -> filmItem.id == id }
            film != null
        }
        return film
    }
}

