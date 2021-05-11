package com.example.whattosee.model

data class FilmDTO(
    val id:Int?,
    val id_kinopoisk:Int?,
    val title:String?,
    val title_alternative:String?,
    val description:String?,
    val actors: List<String?>?,
    val countries:List<String?>?,
    val budget:String?,
    val trailer:String?,
    val poster:String?,
    val rating_kinopoisk:String?,
    val rating_imdb:String?,
    val premiere_world:String?,
    val year:Int?,
)
