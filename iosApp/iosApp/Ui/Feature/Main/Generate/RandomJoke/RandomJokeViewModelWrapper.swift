//
//  RandomJokeViewModelWrapper.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Combine
import CommonKmp

class RandomJokeViewModelWrapper: ObservableObject {
    var viewModel = ViewModelProvider().getRandomJokeViewModel()

    @Published var state: RandomJokeState = RandomJokeState(
        joke: nil,
        loadState: LoadStateInitial()
    )

    @MainActor
    func collectStateFlow() async {
        let collector = FlowCollectorWrapper<RandomJokeState> { newState in
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
    func collectActionFlow(handler: @escaping (RandomJokeAction) -> Void) async {
        let collector = FlowCollectorWrapper<RandomJokeAction> { action in
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
