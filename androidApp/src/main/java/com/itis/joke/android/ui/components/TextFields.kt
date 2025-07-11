package com.itis.joke.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itis.joke.android.ui.theme.JokeTheme

@Composable
fun JokeTextField(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        supportingText = if (supportingText != null) {
            @Composable { Text(supportingText) }
        } else null,
        isError = supportingText != null,
        shape = RoundedCornerShape(8.dp),
        singleLine = singleLine,
    )
}

@Composable
fun PasswordTextField(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        supportingText = if (supportingText != null) {
            @Composable { Text(supportingText) }
        } else null,
        isError = supportingText != null,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        visualTransformation = if (isPasswordVisible.not()) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            ShowHidePasswordIcon(
                isVisible = isPasswordVisible,
                toggleIsVisible = {
                    isPasswordVisible = isPasswordVisible.not()
                },
            )
        }
    )
}

@Composable
fun SearchTextField(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        supportingText = if (supportingText != null) {
            @Composable { Text(supportingText) }
        } else null,
        isError = supportingText != null,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        leadingIcon = {
            JokeIcon(Icons.Default.Search, 24.dp, tint = MaterialTheme.colorScheme.onSurface)
        }
    )
}

@Composable
private fun ShowHidePasswordIcon(
    isVisible: Boolean,
    toggleIsVisible: () -> Unit,
) = IconButton(
    onClick = toggleIsVisible
) {
    Icon(if (isVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility, null)
}


@Preview(showBackground = true)
@Composable
private fun PreviewTextInput() {
    JokeTheme {
        Column {
            JokeTextField(value = "", label = "Email", onValueChange = {})
            PasswordTextField(value = "", label = "Email", onValueChange = {})
            SearchTextField(value = "", label = "") { }
        }
    }
}