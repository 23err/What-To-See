package com.example.whattosee.model

class RepositoryImpl : Repository {
    val categories: ArrayList<Category>

    init {
        val films = listOf(
            Film(
                1,
                "День Сурка",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),
            Film(
                2,
                "Harry Potter",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),
            Film(
                3,
                "Harry Potter",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),
            Film(
                4,
                "Harry Potter",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),

            Film(
                5,
                "Harry Potter",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),
            Film(
                6,
                "Harry Potter",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),
            Film(
                7,
                "Harry Potter",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),
            Film(
                8,
                "Harry Potter",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),
            Film(
                9,
                "Harry Potter",
                9.2F,
                9300000F,
                "История о парне, который попал в петлю времени и проживал раз за разом один и тот же день...",
                "USA"
            ),
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

    override fun getCategory(id: Int): Category? {
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