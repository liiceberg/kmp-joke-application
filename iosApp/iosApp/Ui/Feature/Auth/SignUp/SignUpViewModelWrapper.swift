//
//  SignUpViewModelWrapper.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Combine
import CommonKmp

class SignUpViewModelWrapper: ObservableObject {
    var viewModel = ViewModelProvider().getSignUpViewModel()

    @Published var state: SignUpState = SignUpState(
        username: "",
        usernameValidation: ValidationResult.Companion().empty(),
        email: "",
        emailValidation: ValidationResult.Companion().empty(),
        password: "",
        passwordValidation: ValidationResult.Companion().empty(),
        confirmPassword: "",
        confirmPasswordValidation: ValidationResult.Companion().empty(),
        loadState: LoadStateInitial()
    )

    @MainActor
    func collectStateFlow() async {
        let collector = FlowCollectorWrapper<SignUpState> { newState in
            if let newState {
                self.state = newState
            }
        }

        do {
            try await viewModel.viewStates().collect(collector: collector)
        } catch {
            print("Error collecting state flow: \(error)")
        }
    }


    @MainActor
    func collectActionFlow(handler: @escaping (AuthActionEnum) -> Void) async {
        let collector = FlowCollectorWrapper<SignUpAction> { action in
            if let action {
                switch onEnum(of: action) {
                case is SignUpActionRedirectOnSuccess:
                    handler(.toSignIn)
                default: handler(.toSignIn)
                }
        
            }
        }

        do {
            try await viewModel.viewActions().collect(collector: collector)
        } catch {
            print("Error collecting action flow: \(error)")
        }
    }
    
    deinit {
        viewModel.onCleared()
    }
}
