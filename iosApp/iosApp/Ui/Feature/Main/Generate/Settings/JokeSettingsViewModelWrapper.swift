//
//  JokeSettingsViewModelWrapper.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//


import Foundation
import Combine
import CommonKmp

class JokeSettingsViewModelWrapper: ObservableObject {
    var viewModel = ViewModelProvider().getSettingsViewModel()

    @Published var state: JokeSettingsState = JokeSettingsState(
        category: JokeCategory.any,
        type: JokeType.single,
        blackList: []
    )

    @MainActor
    func collectStateFlow() async {
        let collector = FlowCollectorWrapper<JokeSettingsState> { newState in
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
    func collectActionFlow(handler: @escaping (JokeSettingsAction) -> Void) async {
        let collector = FlowCollectorWrapper<JokeSettingsAction> { action in
            if let action {
                switch onEnum(of: action) {
                default:
                    break
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
