package com.itis.joke.android.ui.feature.random_joke

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.joke.android.R
import com.itis.joke.android.ui.components.JokeCard
import com.itis.joke.android.ui.components.JokeIcon
import com.itis.joke.android.ui.components.LoadingIndicator
import com.itis.joke.android.ui.components.TitleMediumText
import com.itis.joke.android.ui.theme.JokeTheme
import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.core.ui.LoadState
import com.itis.joke.feature.random_joke.domain.SingleJokeModel
import com.itis.joke.feature.random_joke.domain.TwoPartJokeModel
import com.itis.joke.feature.random_joke.presentation.RandomJokeEvent
import com.itis.joke.feature.random_joke.presentation.RandomJokeState
import com.itis.joke.feature.random_joke.presentation.RandomJokeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RandomJokeScreen(viewModel: RandomJokeViewModel = koinViewModel()) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    RandomJokeView(
        state = state,
        { viewModel.obtainEvent(RandomJokeEvent.OnGenerateClick) }
    )
}

@Composable
fun RandomJokeView(state: RandomJokeState, onGenerateClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 24.dp, end = 24.dp)
    ) {
        state.joke?.let {
            when (it.type) {
                JokeType.SINGLE -> {
                    it as SingleJokeModel
                    JokeCard(it.joke)
                }

                JokeType.TWOPART -> {
                    it as TwoPartJokeModel
                    Column {
                        JokeCard(it.setup)
                        Spacer(Modifier.size(16.dp))
                        JokeCard(it.delivery, cardColor = MaterialTheme.colorScheme.tertiary, textColor = MaterialTheme.colorScheme.onTertiary)
                    }
                }

                else -> {}
            }

        }

        Button(
            onClick = onGenerateClick,
            shape = CircleShape,
            modifier = Modifier
                .padding(bottom = 48.dp)
                .align(Alignment.BottomCenter),
        ) {
            TitleMediumText(text = stringResource(R.string.generate_joke), Modifier.padding(8.dp))
            Spacer(Modifier.size(8.dp))
            if (state.loadState == LoadState.Loading) {
                LoadingIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                JokeIcon(
                    icon = Icons.Default.AutoAwesome,
                    size = 24.dp,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
private fun RandomJokePreview() {
    JokeTheme {
        RandomJokeView(RandomJokeState(joke = TwoPartJokeModel("First part", "Second part"))) { }
    }
}