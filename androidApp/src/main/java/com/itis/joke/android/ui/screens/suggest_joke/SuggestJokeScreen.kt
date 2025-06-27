package com.itis.joke.android.ui.screens.suggest_joke

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.joke.android.R
import com.itis.joke.android.ui.components.BlacklistList
import com.itis.joke.android.ui.components.JokeButton
import com.itis.joke.android.ui.components.JokeCategoriesList
import com.itis.joke.android.ui.components.JokeTextField
import com.itis.joke.android.ui.components.LoadingIndicator
import com.itis.joke.android.ui.theme.JokeTheme
import com.itis.joke.android.ui.util.showShortToast
import com.itis.joke.core.common.model.JokeBlackListItem
import com.itis.joke.core.common.model.JokeCategory
import com.itis.joke.core.ui.LoadState
import com.itis.joke.feature.suggest_joke.presentation.SuggestJokeAction
import com.itis.joke.feature.suggest_joke.presentation.SuggestJokeEvent
import com.itis.joke.feature.suggest_joke.presentation.SuggestJokeState
import com.itis.joke.feature.suggest_joke.presentation.SuggestJokeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SuggestJokeScreen(viewModel: SuggestJokeViewModel = koinViewModel()) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    SuggestJokeView(
        state,
        onJokeChanged = { viewModel.obtainEvent(SuggestJokeEvent.OnJokeFilled(it)) },
        onAdditionalPartChanged = {
            viewModel.obtainEvent(
                SuggestJokeEvent.OnJokeAdditionalPartFilled(
                    it
                )
            )
        },
        onCategoryChange = { viewModel.obtainEvent(SuggestJokeEvent.OnCategoryChange(it)) },
        onBlackListChange = { item, checked ->
            viewModel.obtainEvent(SuggestJokeEvent.OnBlackListChange(item, checked))
        },
        onSubmit = { viewModel.obtainEvent(SuggestJokeEvent.OnSubmit) },
    )

    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.viewActions().collect { action ->
            when (action) {

                is SuggestJokeAction.ShowSubmitToast -> {
                    val message = if (action.isSuccess) {
                        R.string.success_submit_text
                    } else {
                        R.string.failure_submit_text
                    }
                    context.showShortToast(message)
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun SuggestJokeView(
    state: SuggestJokeState,
    onJokeChanged: (String) -> Unit,
    onAdditionalPartChanged: (String) -> Unit,
    onSubmit: () -> Unit,
    onCategoryChange: (value: JokeCategory) -> Unit,
    onBlackListChange: (item: JokeBlackListItem, checked: Boolean) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                JokeTextField(
                    value = state.joke,
                    onValueChange = onJokeChanged,
                    label = stringResource(R.string.your_joke_label),
                    singleLine = false,
                )

                JokeTextField(
                    value = state.jokeAdditionalPart,
                    onValueChange = onAdditionalPartChanged,
                    label = stringResource(R.string.additional_joke_part_label),
                    singleLine = false
                )
                Spacer(modifier = Modifier.height(8.dp))

                JokeCategoriesList(state.category, onCategoryChange)
                Spacer(modifier = Modifier.height(8.dp))
                BlacklistList(state.blackList, onBlackListChange)
            }

            JokeButton(
                onClick = onSubmit,
                enabled = state.joke.isNotBlank() && state.loadState != LoadState.Loading,
                text = stringResource(R.string.submit_button),
                modifier = Modifier
            )
        }
        state.loadState.let { loadState ->
            when (loadState) {
                is LoadState.Loading -> LoadingIndicator()
                else -> {}
            }
        }
    }
}

@Preview
@Composable
private fun SuggestJokePreview() {
    JokeTheme {
        SuggestJokeView(SuggestJokeState(), {}, {}, {}, {}, { _, _ -> })
    }
}