package com.example.whattosee

import android.content.Context
import android.view.View
import com.example.whattosee.model.Film
import com.example.whattosee.model.FilmDTO
import com.example.whattosee.model.PageDTO
import com.google.android.material.snackbar.Snackbar
import java.sql.Date
import java.text.SimpleDateFormat

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.showSnackBar(
    message: String,
    action: View.OnClickListener? = null,
    actionText: String = "",
    duration: Int = Snackbar.LENGTH_INDEFINITE,
) {
    Snackbar.make(this, message, duration)
        .apply { action?.let { setAction(actionText, it) } }
        .show()
}

fun FilmDTO.toFilm(): Film  = with(this){
    Film(
        id ?: 0,
        title ?: "",
        original_title ?: "-",
        vote_average?.toString() ?: "-",
        overview ?: "",
        "https://image.tmdb.org/t/p/w500" + backdrop_path,
        budget?.let { if (it == 0) "-" else it.toString() } ?: "-",
        release_date ?: "-",

    )
}

fun PageDTO.toFilms() = this.results.map { it.toFilm() }

fun Date.format(): String{
    val format = SimpleDateFormat("dd.MM.yyyy HH:mm")
    return format.format(this)
}