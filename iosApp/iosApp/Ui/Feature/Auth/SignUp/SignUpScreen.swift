//
//  SignUpScreen.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Combine
import CommonKmp

struct SignUpScreen: View {
    @StateObject private var viewModelWrapper = SignUpViewModelWrapper()
    var toSignIn: () -> Void
    
    var body: some View {
        SignUpView(
            state: viewModelWrapper.state,
            onUsernameFilled: { username in
                viewModelWrapper.viewModel.obtainEvent(event: SignUpEventOnUsernameFilled(username: username))
            },
            onEmailFilled: { email in
                viewModelWrapper.viewModel.obtainEvent(event: SignUpEventOnEmailFilled(email: email))
            },
            onPasswordFilled: { password in
                viewModelWrapper.viewModel.obtainEvent(event: SignUpEventOnPasswordFilled(password: password))
            },
            onConfirmPasswordFilled: { password in
                viewModelWrapper.viewModel.obtainEvent(event: SignUpEventOnConfirmPasswordFilled(password: password))
            },
            onSignUpClicked: {
                viewModelWrapper.viewModel.obtainEvent(event: SignUpEventOnSignUp())
            },
            toSignIn: toSignIn
        )
        .task {
            await viewModelWrapper.collectStateFlow()
        }
        .task {
            await viewModelWrapper.collectActionFlow { action in
                switch action {
                case .toSignIn:
                    toSignIn()
                default: toSignIn()
                }
            }
        }
    }
}

struct SignUpView: View {
    var state: SignUpState
    var onUsernameFilled: (String) -> Void
    var onEmailFilled: (String) -> Void
    var onPasswordFilled: (String) -> Void
    var onConfirmPasswordFilled: (String) -> Void
    var onSignUpClicked: () -> Void
    var toSignIn: () -> Void
    
    var body: some View {
        ZStack {
            Color.background.edgesIgnoringSafeArea(.all)
            
            VStack(alignment: .center, spacing: 24) {
                HStack {
                    Button(action: toSignIn) {
                        Image(systemName: "arrow.backward")
                            .frame(width: 24, height: 24)
                    }
                    
                    Spacer()
                    
                    Image("laugh-squint-svgrepo-com")
                        .resizable()
                        .frame(width: 28, height: 28)
                }
                .padding(.top, 16)
                
                Text(String(localized: "create_account"))
                    .font(.largeTitle)
                    .padding(.top, 72)
                
                VStack(spacing: 16) {
                    JokeTextField(
                        placeholder: String(localized: "username"),
                        text: Binding(
                            get: { state.username },
                            set: { onUsernameFilled($0) }
                        ),
                        onCommit: {},
                        autocapitalization: .none
                    )
                    
                    if let error = state.usernameValidation.error {
                        Text(error).foregroundColor(.red).font(.caption)
                    }
                    
                    JokeTextField(
                        placeholder: String(localized: "email"),
                        text: Binding(
                            get: { state.email },
                            set: { onEmailFilled($0) }
                        ),
                        onCommit: {},
                        autocapitalization: .none
                    )
                    
                    if let error = state.emailValidation.error {
                        Text(error).foregroundColor(.red).font(.caption)
                    }
                    
                    SecureField(String(localized: "password"), text: Binding(
                        get: { state.password },
                        set: { onPasswordFilled($0) }
                    ))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    
                    if let error = state.passwordValidation.error {
                        Text(error).foregroundColor(.red).font(.caption)
                    }
                    
                    SecureField(String(localized: "confirm_password"), text: Binding(
                        get: { state.confirmPassword },
                        set: { onConfirmPasswordFilled($0) }
                    ))
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    
                    if let error = state.confirmPasswordValidation.error {
                        Text(error).foregroundColor(.red).font(.caption)
                    }
                }
                .padding(.top, 72)
                
                let enableButton = state.usernameValidation.isValid &&
                    state.emailValidation.isValid &&
                    state.passwordValidation.isValid &&
                    state.confirmPasswordValidation.isValid
                
                JokeButton(
                    title: String(localized: "sign_up"),
                    isEnabled: enableButton,
                    action: onSignUpClicked
                )
                .padding(.top, 32)
                
                HStack {
                    Text(String(localized: "have_account_question"))
                    Button(action: toSignIn) {
                        Text(String(localized: "sign_in"))
                            .foregroundColor(.primary)
                            .underline()
                    }
                }
                .padding(.top, 48)
                
                Spacer()
            }
            .padding(.horizontal, 16)
            
            if state.loadState is LoadStateLoading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle())
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .background(Color.black.opacity(0.3))
            }
            
            if let errorState = state.loadState as? LoadStateError {
                Text(errorState.message)
                    .padding()
                    .background(Color.red.opacity(0.8))
                    .foregroundColor(.white)
                    .cornerRadius(8)
                    .padding()
                    .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            }
        }
    }
}
