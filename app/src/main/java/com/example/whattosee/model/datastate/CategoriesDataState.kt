package com.example.whattosee.model.datastate

import android.os.Parcelable
import com.example.whattosee.model.Category
import kotlinx.android.parcel.Parcelize

sealed class CategoriesDataState: Parcelable {
    @Parcelize
    data class Success(val categories: List<Category>): CategoriesDataState(), Parcelable
    @Parcelize
    data class Error(val error: Throwable): CategoriesDataState(), Parcelable
    @Parcelize
    object Loading: CategoriesDataState(), Parcelable
}
