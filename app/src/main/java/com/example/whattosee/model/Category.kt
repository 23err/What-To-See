package com.example.whattosee.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(val id: Int, val title: String, val films: List<Film>):Parcelable
