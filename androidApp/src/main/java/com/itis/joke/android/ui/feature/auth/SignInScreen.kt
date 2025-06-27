package com.itis.joke.android.ui.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.itis.joke.android.ui.components.JokeTextField
import com.itis.joke.android.ui.components.LimitedErrorMessage
import com.itis.joke.android.ui.components.LoadingIndicator
import com.itis.joke.android.ui.components.PasswordTextField
import com.itis.joke.android.ui.theme.JokeTheme
import com.itis.joke.core.ui.LoadState
import com.itis.joke.feature.auth.presentation.sign_in.SignInAction
import com.itis.joke.feature.auth.presentation.sign_in.SignInEvent
import com.itis.joke.feature.auth.presentation.sign_in.SignInState
import com.itis.joke.feature.auth.presentation.sign_in.SignInViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel(),
    toSignUp: () -> Unit,
    toMainPage: () -> Unit,
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    SignInView(
        state = state,
        onEmailFilled = { viewModel.obtainEvent(SignInEvent.OnEmailFilled(it)) },
        onPasswordFilled = { viewModel.obtainEvent(SignInEvent.OnPasswordFilled(it)) },
        onSignInClicked = { viewModel.obtainEvent(SignInEvent.OnSignIn) },
        toSignUp = toSignUp,
    )

    LaunchedEffect(Unit) {
        viewModel.viewActions().collect { action ->
            when (action) {
                is SignInAction.GoToMainPage -> toMainPage()
                else -> {}
            }
        }
    }
}

@Composable
private fun SignInView(
    state: SignInState,
    onEmailFilled: (email: String) -> Unit,
    onPasswordFilled: (password: String) -> Unit,
    onSignInClicked: () -> Unit,
    toSignUp: () -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        with(state) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                JokeIcon(painterResource(R.drawable.laugh_logo), 72.dp, Modifier.padding(top = 72.dp))
                HeadlineLargeText(
                    text = stringResource(id = R.string.sign_in_title_text),
                    Modifier.padding(top = 72.dp)
                )
                JokeTextField(
                    value = email,
                    label = stringResource(id = R.string.email_label),
                    supportingText = emailValidation.error,
                    modifier = Modifier.padding(top = 72.dp)
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

                val enableButtons = emailValidation.isValid && passwordValidation.isValid

                JokeButton(
                    text = stringResource(id = R.string.sign_in),
                    enabled = enableButtons,
                    modifier = Modifier.padding(top = 32.dp)
                ) {
                    onSignInClicked()
                }
                BodyTextWithLink(
                    stringResource(id = R.string.no_account),
                    stringResource(id = R.string.sign_up),
                    modifier = Modifier.padding(top = 48.dp)
                ) {
                    toSignUp()
                }
            }
            when(loadState){
                is LoadState.Error -> LimitedErrorMessage(errorText = (loadState as LoadState.Error).message)
                is LoadState.Loading -> LoadingIndicator()
                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInPreview() {
    JokeTheme {
        SignInView(SignInState(), {}, {}, {}, {})
    }
}