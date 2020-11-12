package dev.adambennett.design.system.frontend.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThemeModel(
    val primary: ColorModel,
    val primaryVariant: ColorModel,
    val onPrimary: ColorModel,
    val secondary: ColorModel,
    val onSecondary: ColorModel,
    val surface: ColorModel,
    val onSurface: ColorModel,
    val onBackground: ColorModel,
    val error: ColorModel,
    val onError: ColorModel,
)

@JsonClass(generateAdapter = true)
data class ColorModel(val value: Int)
