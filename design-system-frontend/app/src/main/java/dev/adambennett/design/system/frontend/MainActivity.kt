package dev.adambennett.design.system.frontend

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants.defaultButtonColors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.adambennett.design.system.frontend.ui.DesignSystemTheme

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(viewModel)
        }
    }
}

@Composable
private fun MainScreen(viewModel: MainViewModel) {
    DesignSystemTheme {
        val designSystemState by viewModel.colorState.collectAsState()

        when (designSystemState) {
            DesignSystemState.Default -> Unit
            is DesignSystemState.Updated -> {
                // Smart cast doesn't work here
                val updated = designSystemState as DesignSystemState.Updated
                DesignSystemTheme.colors.update(updated.colors)
            }
        }

        MainLayout(viewModel::updateDesignSystem)
    }
}

@Composable
private fun MainLayout(click: () -> Unit = {}) {
    DesignSystemSurface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            DesignSystemText(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = "Hello, design system!"
            )

            DesignSystemButton(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                click = click
            ) {
                Text(text = "Load theme")
            }
        }
    }
}

@Composable
private fun DesignSystemSurface(modifier: Modifier, content: @Composable () -> Unit) {
    Surface(
        modifier = modifier,
        color = DesignSystemTheme.colors.surface,
        content = content,
    )
}

@Composable
private fun DesignSystemButton(
    modifier: Modifier,
    click: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        modifier = modifier,
        colors = defaultButtonColors(
            backgroundColor = DesignSystemTheme.colors.secondary,
            contentColor = DesignSystemTheme.colors.onSecondary
        ),
        content = content,
        onClick = click
    )
}

@Composable
private fun DesignSystemText(
    modifier: Modifier,
    text: String,
) {
    BasicText(
        text = text,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun Preview() {
    DesignSystemTheme {
        MainLayout()
    }
}
