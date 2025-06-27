package com.itis.joke.android.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itis.joke.android.R
import com.itis.joke.android.ui.components.BlacklistList
import com.itis.joke.android.ui.components.JokeButton
import com.itis.joke.android.ui.components.JokeCategoriesList
import com.itis.joke.android.ui.components.JokeIconButton
import com.itis.joke.android.ui.components.RadioItem
import com.itis.joke.android.ui.components.TitleLargeText
import com.itis.joke.android.ui.theme.JokeTheme
import com.itis.joke.android.ui.util.showShortToast
import com.itis.joke.core.common.joke.JokeBlackListItem
import com.itis.joke.core.common.joke.JokeCategory
import com.itis.joke.core.common.joke.JokeType
import com.itis.joke.feature.joke_settings.presenation.JokeSettingsAction
import com.itis.joke.feature.joke_settings.presenation.JokeSettingsEvent
import com.itis.joke.feature.joke_settings.presenation.JokeSettingsState
import com.itis.joke.feature.joke_settings.presenation.JokeSettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun JokeSettingsScreen(viewModel: JokeSettingsViewModel = koinViewModel(), onClose: () -> Unit) {

    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    JokeSettingsView(
        state,
        onSave = { viewModel.obtainEvent(JokeSettingsEvent.OnSave) },
        onJokeTypeChange = { viewModel.obtainEvent(JokeSettingsEvent.OnJokeTypeChange(it)) },
        onCategoryChange = { viewModel.obtainEvent(JokeSettingsEvent.OnCategoryChange(it)) },
        onBlackListChange = { item, checked ->
            viewModel.obtainEvent(JokeSettingsEvent.OnBlackListChange(item, checked))
        },
        onClose = onClose,
    )

    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.viewActions().collect { action ->
            when (action) {

                is JokeSettingsAction.ShowSaveToast -> {
                    val message = if (action.isSuccess) {
                        R.string.success_settings_text
                    } else {
                        R.string.failure_settings_text
                    }
                    context.showShortToast(message)
                    onClose()
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun JokeSettingsView(
    state: JokeSettingsState,
    onSave: () -> Unit,
    onCategoryChange: (value: JokeCategory) -> Unit,
    onJokeTypeChange: (value: JokeType) -> Unit,
    onBlackListChange: (item: JokeBlackListItem, checked: Boolean) -> Unit,
    onClose: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            JokeIconButton(icon = Icons.Default.Close, size = 32.dp, onClick = onClose)
        }
        JokeCategoriesList(state.category, onCategoryChange)

        Spacer(modifier = Modifier.height(16.dp))
        TitleLargeText(
            text = stringResource(R.string.joke_type)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            JokeType.entries.forEach { type ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onJokeTypeChange(type) }
                ) {
                    RadioItem(type.name, state.type == type) { onJokeTypeChange(type) }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        BlacklistList(state.blackList, onBlackListChange)

        Spacer(modifier = Modifier.weight(1f))

        JokeButton(text = stringResource(R.string.save_button), onClick = onSave)
    }
}






@Preview(showBackground = true)
@Composable
private fun JokeSettingsPreview() {
    JokeTheme {
        JokeSettingsView(JokeSettingsState(), {}, {}, {}, { _, _ -> }, {})
    }
}