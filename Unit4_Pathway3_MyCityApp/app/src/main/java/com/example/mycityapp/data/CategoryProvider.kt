package com.example.mycityapp.data

import com.example.mycityapp.R
import com.example.mycityapp.model.Category

object CategoryProvider {
    val defaultCategory = getCategoryData()[0]

    fun getCategoryData(): List<Category> = listOf(
        R.string.category1,
        R.string.category2,
        R.string.category3,
        R.string.category4,
        R.string.category5,
        R.string.category6,
        R.string.category7,
        R.string.category8,
        R.string.category9,
        R.string.category10
    ).map { titleRes ->
        Category(
            title = titleRes,
            recommendationList = RecommendationProvider.getRecommendationData().shuffled().take((2..5).random())
        )
    }
}