package com.itis.joke.android.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioItem(
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
fun CheckboxItem(
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