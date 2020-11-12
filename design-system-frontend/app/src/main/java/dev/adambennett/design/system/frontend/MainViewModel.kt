package dev.adambennett.design.system.frontend

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.adambennett.design.system.frontend.network.ThemeModel
import dev.adambennett.design.system.frontend.network.ThemeProvider
import dev.adambennett.design.system.frontend.ui.DesignSystemColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _colorState: MutableStateFlow<DesignSystemState> = MutableStateFlow(DesignSystemState.Default)
    val colorState: StateFlow<DesignSystemState> = _colorState

    fun updateDesignSystem() {
        viewModelScope.launch {
            val colors = ThemeProvider.getTheme().toDesignSystem()

            _colorState.emit(DesignSystemState.Updated(colors))
        }
    }
}

sealed class DesignSystemState {

    object Default: DesignSystemState()
    data class Updated(val colors: DesignSystemColors): DesignSystemState()
}

private fun ThemeModel.toDesignSystem(): DesignSystemColors = DesignSystemColors(
    primary = Color(primary.value),
    primaryVariant = Color(primaryVariant.value),
    onPrimary = Color(onPrimary.value),
    secondary = Color(secondary.value),
    onSecondary = Color(onSecondary.value),
    surface = Color(surface.value),
    onSurface = Color(onSurface.value),
    onBackground = Color(onBackground.value),
    error = Color(error.value),
    onError = Color(onError.value)
)
