package com.example.whattosee

import com.example.whattosee.model.Category

sealed class CategoryDataState {
    data class Success(val categories: List<Category>):CategoryDataState()
    data class Error(val error: kotlin.Error):CategoryDataState()
    object Loading:CategoryDataState()
}
