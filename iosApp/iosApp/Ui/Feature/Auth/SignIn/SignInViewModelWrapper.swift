//
//  SignInViewModel.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//



import Foundation
import Combine
import CommonKmp

class SignInViewModelWrapper: ObservableObject {
    var viewModel = ViewModelProvider().getSignInViewModel()

    @Published var state: SignInState = SignInState(
        email: "",
        emailValidation: ValidationResult.Companion().empty(),
        password: "",
        passwordValidation: ValidationResult.Companion().empty(),
        loadState: LoadStateInitial()
    )

    @MainActor
    func collectStateFlow() async {
        let collector = FlowCollectorWrapper<SignInState> { newState in
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
        let collector = FlowCollectorWrapper<SignInAction> { action in
            if let action {
                switch onEnum(of: action) {
                case SignInAction.GoToMainPage():
                    handler(.toMain)
                default: handler(.toMain)
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
