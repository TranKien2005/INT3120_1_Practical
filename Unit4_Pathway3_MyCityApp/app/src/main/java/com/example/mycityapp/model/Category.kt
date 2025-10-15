package com.example.mycityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mycityapp.R

data class Category(
    @StringRes val title : Int,
    val recommendationList : List<Recommendation>,
    @DrawableRes val image: Int = R.drawable.category_sample
)
