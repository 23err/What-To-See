package com.example.whattosee.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    val rating: String,
    val budget:String,
    val description:String,
    val madeIn:String = "",
    val image: String = "",
) : Parcelable