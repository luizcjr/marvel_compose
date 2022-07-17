package com.example.marvelcompose.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.marvelcompose.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val fontFamily = FontFamily(
    Font(R.font.gilroy_light)
)

val profileTitle = TextStyle(
    fontSize = 40.sp,
    fontWeight = FontWeight.Black,
    color = TextColor,
    fontFamily = fontFamily
)

val homeTitle = TextStyle(
    fontSize = 32.sp,
    fontWeight = FontWeight.Black,
    color = TextColor,
    fontFamily = fontFamily
)

val cardTitle = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Black,
    color = TextColor,
    fontFamily = fontFamily
)


val sectionTitle = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    color = TextColor,
    fontFamily = fontFamily
)


val profileSubtitle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
    color = TextColor,
    fontFamily = fontFamily
)

val homeSubtitle = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold,
    color = TextColor,
    fontFamily = fontFamily
)

val description = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Medium,
    color = TextColor,
    fontFamily = fontFamily
)

val caracteristc = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Medium,
    color = TextColor,
    fontFamily = fontFamily
)

val ability = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    color = TextColor,
    fontFamily = fontFamily
)

val cardSubtitle = TextStyle(
    fontSize = 10.sp,
    fontWeight = FontWeight.Medium,
    color = TextColor,
    fontFamily = fontFamily
)