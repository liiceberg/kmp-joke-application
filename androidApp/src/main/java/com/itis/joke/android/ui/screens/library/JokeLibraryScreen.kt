package com.itis.joke.android.ui.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
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
import com.itis.joke.android.ui.components.ErrorMessage
import com.itis.joke.android.ui.components.LoadingIndicator
import com.itis.joke.android.ui.components.SearchTextField
import com.itis.joke.android.ui.components.TitleLargeText
import com.itis.joke.android.ui.theme.JokeTheme
import com.itis.joke.core.ui.LoadState
import com.itis.joke.feature.library.presenation.JokeLibraryEvent
import com.itis.joke.feature.library.presenation.JokeLibraryState
import com.itis.joke.feature.library.presenation.JokeLibraryViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun JokeLibraryScreen(viewModel: JokeLibraryViewModel = koinViewModel()) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    JokeLibraryView(
        state = state,
        { viewModel.obtainEvent(JokeLibraryEvent.OnSearchQueryChange(it)) }
    )
}

@Composable
private fun JokeLibraryView(
    state: JokeLibraryState,
    onSearchFilled: (String) -> Unit,
) {

    Column(
        Modifier.fillMaxSize(),
    ) {
        SearchTextField(
            state.query,
            stringResource(R.string.search),
            Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
        ) { onSearchFilled(it) }
        JokeList(state.items)
        Box(Modifier.fillMaxSize(), Alignment.Center) {
            when (state.loadState) {
                LoadState.Loading -> LoadingIndicator()
                is LoadState.Error -> ErrorMessage(errorText = (state.loadState as LoadState.Error).message)
                else -> {}
            }
        }
    }

}

@Composable
fun JokeList(items: List<String>) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        items(items.size) { index ->
            JokeItem(items[index])
            if (index < items.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun JokeItem(joke: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TitleLargeText(joke, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

@Preview
@Composable
private fun LibraryPreview() {
    JokeTheme {
        JokeLibraryView(JokeLibraryState(items = listOf("joke 1"))) {}
    }
}
