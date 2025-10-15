package com.example.mycityapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mycityapp.R

val roboto = FontFamily(
    Font(R.font.roboto, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium)
)

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

