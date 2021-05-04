package com.example.whattosee

import com.example.whattosee.model.Category

sealed class CategoriesDataState {
    data class Success(val categories: List<Category>):CategoriesDataState()
    data class Error(val error: kotlin.Error):CategoriesDataState()
    object Loading:CategoriesDataState()
}
