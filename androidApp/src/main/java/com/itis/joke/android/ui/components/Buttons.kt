package com.itis.joke.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.itis.joke.android.ui.theme.JokeTheme

@Composable
fun JokeButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(onClick = onClick, modifier.fillMaxWidth(), enabled = enabled) {
        TitleSmallText(text = text)
    }
}

@Composable
fun JokeIconButton(
    icon: Painter,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        JokeIcon(painter = icon, size = size, modifier = modifier, tint = tint)
    }
}

@Composable
fun JokeIconButton(
    icon: ImageVector,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
) {
    JokeIconButton(
        icon = rememberVectorPainter(image = icon),
        size = size,
        modifier = modifier,
        tint = tint,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewButton() {
    JokeTheme {
        Column {
            JokeButton(text = "Example") {}
        }
    }
}

