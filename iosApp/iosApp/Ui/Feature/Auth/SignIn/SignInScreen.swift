import SwiftUI
import Combine
import CommonKmp

struct SignInScreen: View {
    @StateObject private var viewModelWrapper = SignInViewModelWrapper()
    var toSignUp: () -> Void
    var toMainPage: () -> Void
    
    var body: some View {
        SignInView(
            state: viewModelWrapper.state,
            onEmailFilled: { email in
                viewModelWrapper.viewModel.obtainEvent(event: SignInEventOnEmailFilled(email: email))
            },
            onPasswordFilled: { password in
                viewModelWrapper.viewModel.obtainEvent(event: SignInEventOnPasswordFilled(password: password))
            },
            onSignInClicked: {
                viewModelWrapper.viewModel.obtainEvent(event: SignInEventOnSignIn())
            },
            toSignUp: toSignUp
        )
        .task {
            await viewModelWrapper.collectStateFlow()
        }
        .task {
            await viewModelWrapper.collectActionFlow { action in
                switch action {
                case .toMain:
                    toMainPage()
                default: toMainPage()
                }
            }
        }
    }
}

struct SignInView: View {
    var state: SignInState
    var onEmailFilled: (String) -> Void
    var onPasswordFilled: (String) -> Void
    var onSignInClicked: () -> Void
    var toSignUp: () -> Void
    
    var body: some View {
        ZStack {
            Color.background.edgesIgnoringSafeArea(.all)
            VStack(alignment: .center, spacing: 24) {
                Spacer().frame(height: 72)
                
                Image("laugh-squint-svgrepo-com")
                    .resizable()
                    .frame(width: 72, height: 72)
                
                Text(String(localized: "sign_in_account"))
                    .font(.largeTitle)
                    .padding(.top, 72)
                
                VStack(spacing: 16) {
                    JokeTextField(
                        placeholder: "Email",
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
                }
                .padding(.top, 72)
                
                JokeButton(
                    title: String(localized: "sign_in"),
                    isEnabled: state.emailValidation.isValid && state.passwordValidation.isValid,
                    action: onSignInClicked,
                )
                .padding(.top, 32)
                
                HStack {
                    Text(String(localized: "no_account_question"))
                    Button(action: toSignUp) {
                        Text("Sign Up")
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
