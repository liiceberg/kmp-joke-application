package com.itis.joke.android.ui.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
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
import com.itis.joke.android.ui.components.BodyMediumText
import com.itis.joke.android.ui.components.JokeButton
import com.itis.joke.android.ui.components.TitleLargeText
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
fun JokeSettingsScreen(viewModel: JokeSettingsViewModel = koinViewModel()) {

    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    JokeSettingsView(
        state,
        onSave = { viewModel.obtainEvent(JokeSettingsEvent.OnSave) },
        onJokeTypeChange = { viewModel.obtainEvent(JokeSettingsEvent.OnJokeTypeChange(it)) },
        onCategoryChange = { viewModel.obtainEvent(JokeSettingsEvent.OnCategoryChange(it)) },
        onBlackListChange = { item, checked ->
            viewModel.obtainEvent(JokeSettingsEvent.OnBlackListChange(item, checked))
        }
    )

    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.viewActions().collect { action ->
            when (action) {

                is JokeSettingsAction.ShowSaveToast -> {
                    val message =
                        if (action.isSuccess) R.string.success_text else R.string.failure_text
                    context.showShortToast(message)
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
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {

        TitleLargeText(
            text = stringResource(R.string.joke_category),
        )

        JokeCategory.entries.forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategoryChange(category) }
            ) {
                RadioItem(category.name, state.category == category) { onCategoryChange(category) }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
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


        Spacer(modifier = Modifier.height(8.dp))
        TitleLargeText(
            text = stringResource(R.string.blacklist),
        )

        JokeBlackListItem.entries.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                CheckboxItem(
                    item.name,
                    state.blackList.contains(item)
                ) { onBlackListChange(item, it) }
            }
        }


        Spacer(modifier = Modifier.weight(1f))

        JokeButton(text = stringResource(R.string.save_button), onClick = onSave)
    }
}

@Composable
private fun RadioItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    RadioButton(
        selected = selected,
        onClick = onClick
    )
    BodyMediumText(
        text = text,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
private fun CheckboxItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Checkbox(
        checked = checked,
        onCheckedChange = { checked ->
            onCheckedChange(checked)
        }
    )
    BodyMediumText(
        text = text,
        modifier = Modifier.padding(start = 8.dp)
    )
}


@Preview(showBackground = true)
@Composable
private fun JokeSettingsPreview() {
    JokeSettingsView(JokeSettingsState(), {}, {}, {}, { _, _ -> })
}