package com.example.mycityapp.ui

import android.R.attr.data
import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.CategoryProvider
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        MyCityState(
            categoryList = CategoryProvider.getCategoryData(),
            currentCategory = CategoryProvider.defaultCategory
        )
    )
    val uiState = _uiState as StateFlow<MyCityState>

    fun updateCurrentCategory(selectedCategory: Category) {
        _uiState.value = _uiState.value.copy(currentCategory = selectedCategory)
    }

    fun updateCurrentRecommendation(selectedRecommendation: Recommendation) {
        _uiState.value = _uiState.value.copy(currentRecommendation = selectedRecommendation)
    }
}
data class MyCityState (
    val categoryList: List<Category> = emptyList(),
    val currentCategory: Category? = CategoryProvider.defaultCategory,
    val currentRecommendation: Recommendation? = currentCategory?.recommendationList?.get(0)
)
