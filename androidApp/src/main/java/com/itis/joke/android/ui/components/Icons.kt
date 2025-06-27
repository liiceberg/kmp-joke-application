package com.itis.joke.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.itis.joke.android.R
import com.itis.joke.android.ui.theme.JokeTheme

@Composable
fun JokeIcon(
    painter: Painter,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = modifier.size(size),
        tint = tint,
    )
}

@Composable
fun JokeIcon(
    icon: ImageVector,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    JokeIcon(
        painter = rememberVectorPainter(image = icon),
        size = size,
        modifier = modifier,
        tint = tint
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewIcons() {

    JokeTheme {
        Column {
            JokeIcon(painter = painterResource(id = R.drawable.laugh_logo), 48.dp)
        }
    }
}


