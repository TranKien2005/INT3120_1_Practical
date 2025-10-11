package com.example.mycityapp.data

import com.example.mycityapp.R
import com.example.mycityapp.model.Recommendation

object RecommendationProvider {
    val defaultRecommendation = getRecommendationData()[0]

    fun getRecommendationData(): List<Recommendation> {
        return listOf(
            Recommendation(R.string.title1, R.string.description1),
            Recommendation(R.string.title2, R.string.description2),
            Recommendation(R.string.title3, R.string.description3),
            Recommendation(R.string.title4, R.string.description4),
            Recommendation(R.string.title5, R.string.description5),
            Recommendation(R.string.title6, R.string.description6),
            Recommendation(R.string.title7, R.string.description7),
            Recommendation(R.string.title8, R.string.description8),
            Recommendation(R.string.title9, R.string.description9),
            Recommendation(R.string.title10, R.string.description10),
        )
    }
}