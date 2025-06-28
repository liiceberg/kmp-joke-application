//
//  SuggestJokeViewModelWrapper.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Combine
import CommonKmp

class SuggestJokeViewModelWrapper: ObservableObject {
    var viewModel = ViewModelProvider().getSuggestJokeViewModel()

    @Published var state: SuggestJokeState = SuggestJokeState(
        joke: "",
        jokeAdditionalPart: "",
        category: JokeCategory.any,
        blackList: [],
        loadState: LoadStateInitial()
    )

    @MainActor
    func collectStateFlow() async {
        let collector = FlowCollectorWrapper<SuggestJokeState> { newState in
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
    func collectActionFlow(handler: @escaping (SuggestJokeAction) -> Void) async {
        let collector = FlowCollectorWrapper<SuggestJokeAction> { action in
            if let action {
                handler(action)
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
