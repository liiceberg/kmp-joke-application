package com.itis.joke.android.ui.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itis.joke.android.R
import com.itis.joke.android.ui.components.BodyTextWithLink
import com.itis.joke.android.ui.components.HeadlineLargeText
import com.itis.joke.android.ui.components.JokeButton
import com.itis.joke.android.ui.components.JokeIcon
import com.itis.joke.android.ui.components.JokeIconButton
import com.itis.joke.android.ui.components.JokeTextField
import com.itis.joke.android.ui.components.LimitedErrorMessage
import com.itis.joke.android.ui.components.LoadingIndicator
import com.itis.joke.android.ui.components.PasswordTextField
import com.itis.joke.android.ui.theme.JokeTheme
import com.itis.joke.android.ui.util.showShortToast
import com.itis.joke.core.ui.LoadState
import com.itis.joke.feature.auth.presentation.sign_up.SignUpAction
import com.itis.joke.feature.auth.presentation.sign_up.SignUpEvent
import com.itis.joke.feature.auth.presentation.sign_up.SignUpState
import com.itis.joke.feature.auth.presentation.sign_up.SignUpViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = koinViewModel(),
    toSignIn: () -> Unit
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    SignUpView(
        state = state,
        onUsernameFilled = { viewModel.obtainEvent(SignUpEvent.OnUsernameFilled(it)) },
        onEmailFilled = { viewModel.obtainEvent(SignUpEvent.OnEmailFilled(it)) },
        onPasswordFilled = { viewModel.obtainEvent(SignUpEvent.OnPasswordFilled(it)) },
        onConfirmPasswordFilled = { viewModel.obtainEvent(SignUpEvent.OnConfirmPasswordFilled(it)) },
        onSignUpClicked = { viewModel.obtainEvent(SignUpEvent.OnSignUp) },
        toSignIn = toSignIn,
    )

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.viewActions().collect { action ->
            when (action) {
                is SignUpAction.RedirectOnSuccess -> {
                    context.showShortToast(R.string.success_create_account)
                    toSignIn()
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun SignUpView(
    state: SignUpState,
    onUsernameFilled: (username: String) -> Unit,
    onEmailFilled: (email: String) -> Unit,
    onPasswordFilled: (password: String) -> Unit,
    onConfirmPasswordFilled: (password: String) -> Unit,
    onSignUpClicked: () -> Unit,
    toSignIn: () -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        with(state) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    JokeIconButton(
                        icon = Icons.AutoMirrored.Filled.ArrowBack,
                        size = 24.dp
                    ) {
                        toSignIn()
                    }
                    JokeIcon(painterResource(R.drawable.laugh_logo), 28.dp)
                }

                HeadlineLargeText(
                    text = stringResource(id = R.string.sign_up_title_text),
                    Modifier.padding(top = 72.dp)
                )
                JokeTextField(
                    value = username,
                    label = stringResource(id = R.string.username_label),
                    supportingText = usernameValidation.error,
                    modifier = Modifier.padding(top = 72.dp)
                ) {
                    onUsernameFilled(it)
                }

                JokeTextField(
                    value = email,
                    label = stringResource(id = R.string.email_label),
                    supportingText = emailValidation.error
                ) {
                    onEmailFilled(it)
                }
                PasswordTextField(
                    value = password,
                    label = stringResource(id = R.string.password_label),
                    supportingText = passwordValidation.error
                ) {
                    onPasswordFilled(it)
                }
                PasswordTextField(
                    value = confirmPassword,
                    label = stringResource(id = R.string.confirm_password_label),
                    supportingText = confirmPasswordValidation.error
                ) {
                    onConfirmPasswordFilled(it)
                }

                val enableButtons = usernameValidation.isValid && emailValidation.isValid
                        && passwordValidation.isValid && confirmPasswordValidation.isValid

                JokeButton(
                    text = stringResource(id = R.string.sign_up),
                    modifier = Modifier.padding(top = 32.dp),
                    enabled = enableButtons,
                ) {
                    onSignUpClicked()
                }

                BodyTextWithLink(
                    stringResource(id = R.string.have_account),
                    stringResource(id = R.string.sign_in),
                    modifier = Modifier.padding(top = 48.dp)
                ) {
                    toSignIn()
                }
            }
            when(loadState){
                is LoadState.Error -> LimitedErrorMessage(errorText = (loadState as LoadState.Error).message)
                LoadState.Loading -> LoadingIndicator()
                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SigUpPreview() {
    JokeTheme {
        SignUpView(
            SignUpState(),
            {},
            {},
            {},
            {},
            {},
            {},
        )
    }
}