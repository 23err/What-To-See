package com.example.whattosee.model

data class FilmDTO(
    val id: Int?,
    val backdrop_path: String?,
    val original_title:String?,
    val title:String?,
    val vote_average: Float?,
    val overview: String?,
    val budget : Int? = 0,
    val release_date : String? = null,
)
