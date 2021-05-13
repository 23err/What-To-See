package com.example.whattosee.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val rating: String,
    val description:String,
    val image: String = "",
    val budget: String = "",
    val releaseDate: String = "",
) : Parcelable