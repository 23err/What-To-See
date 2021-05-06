package com.example.whattosee.model.datastate

import com.example.whattosee.model.Category

sealed class CategoryDataState {
    data class Success(val category: Category): CategoryDataState()
    data class Error(val error: kotlin.Error): CategoryDataState()
    object Loading: CategoryDataState()
}
