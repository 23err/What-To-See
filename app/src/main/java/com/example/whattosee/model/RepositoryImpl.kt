package com.example.whattosee.model

class RepositoryImpl : Repository {
    val categories: ArrayList<Category>

    init {
        val films = listOf(
                Film(1, "Harry Potter", 9.2F),
                Film(2, "День Сурка", 9.3F),
                Film(3, "Мстители", 6.7F),
                Film(4, "Мстители", 6.7F),
                Film(5, "FILM", 6.7F),
                Film(6, "FILM", 6.7F),
                Film(7, "FILM", 6.7F),
                Film(8, "FILM", 6.7F),
        )
        categories = arrayListOf(
                Category(1, "Все", films),
                Category(2, "По рейтингу", films),
                Category(3, "Смотреть позже", films),
                Category(4, "Оцененные мной", films),
                Category(5, "Просмотренные", films),
        )
    }

    override fun getCategoryList(): ArrayList<Category>? {

        return categories
    }

    override fun getFilmsInCategory(id: Int): Category? {
        return categories.find { it.id == id }
    }

    override fun getFilm(id: Int): Film? {
        var film: Film? = null
        categories.find {
            film = it.films.find { it1 -> it1.id == id }
            film != null
        }
        return film
    }
}