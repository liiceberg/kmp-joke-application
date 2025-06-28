//
//  JokeLibraryViewModelWrapper.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Combine
import CommonKmp

class LibraryViewModelWrapper: ObservableObject {
    var viewModel = ViewModelProvider().getLibraryViewModel()

    @Published var state: JokeLibraryState = JokeLibraryState(
        query: "",
        items: [],
        loadState: LoadStateInitial()
    )

    @MainActor
    func collectStateFlow() async {
        let collector = FlowCollectorWrapper<JokeLibraryState> { newState in
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
    
    deinit {
        viewModel.onCleared()
    }
}
