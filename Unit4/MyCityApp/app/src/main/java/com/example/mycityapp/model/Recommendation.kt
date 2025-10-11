package com.example.mycityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mycityapp.R

data class Recommendation(
    @StringRes val title : Int,
    @StringRes val description : Int,
    @DrawableRes val image : Int = R.drawable.recommendation_sample
)
