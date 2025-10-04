package com.example.a30dayapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    val dayID: String,
    val title: String,
    val description: String,
    val imageRes: Int
)
