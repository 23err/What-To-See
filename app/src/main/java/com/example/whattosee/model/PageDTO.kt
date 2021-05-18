package com.example.whattosee.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PageDTO (
    val page:Int,
    val results: List<FilmDTO>
): Parcelable
