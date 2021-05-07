package com.example.whattosee.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    val rating: Float,
    val budget:Float,
    val description:String,
    val madeIn:String = "",
    val image: String = "",


) : Parcelable