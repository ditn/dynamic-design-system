package dev.adambennett.design.system.frontend.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.graphics.Color

private val LightColorPalette = DesignSystemColors(
    primary = purple500,
    primaryVariant = purple700,
    onPrimary = Color.White,
    secondary = teal200,
    onSecondary = Color.Black,
    surface = Color.White,
    onSurface = Color.Gray,
    onBackground = Color.LightGray,
    error = Color.Red,
    onError = Color.DarkGray
)

@Composable
fun DesignSystemTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    ProvideDesignSystemColors(LightColorPalette) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

object DesignSystemTheme {
    @Composable
    val colors: DesignSystemColors
        get() = AmbientDesignSystemColors.current
}

class DesignSystemColors(
    primary: Color,
    primaryVariant: Color,
    onPrimary: Color,
    secondary: Color,
    onSecondary: Color,
    surface: Color,
    onSurface: Color,
    onBackground: Color,
    error: Color,
    onError: Color
) {
    var primary: Color by mutableStateOf(primary)
        private set
    var primaryVariant: Color by mutableStateOf(primaryVariant)
        private set
    var onPrimary: Color by mutableStateOf(onPrimary)
        private set
    var secondary: Color by mutableStateOf(secondary)
        private set
    var onSecondary: Color by mutableStateOf(onSecondary)
        private set
    var surface: Color by mutableStateOf(surface)
        private set
    var onSurface: Color by mutableStateOf(onSurface)
        private set
    var onBackground: Color by mutableStateOf(onBackground)
        private set
    var error: Color by mutableStateOf(error)
        private set
    var onError: Color by mutableStateOf(onError)
        private set

    fun update(other: DesignSystemColors) {
        primary = other.primary
        primaryVariant = other.primaryVariant
        onPrimary = other.onPrimary
        secondary = other.secondary
        onSecondary = other.onSecondary
        surface = other.surface
        onSurface = other.onSurface
        onBackground = other.onBackground
        error = other.error
        onError = other.onError
    }
}

@Composable
fun ProvideDesignSystemColors(
    colors: DesignSystemColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    Providers(AmbientDesignSystemColors provides colorPalette, children = content)
}

private val AmbientDesignSystemColors = staticAmbientOf<DesignSystemColors> {
    error("No ColorPalette provided")
}

private fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)

