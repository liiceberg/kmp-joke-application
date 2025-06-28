//
//  RootCoordinator.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

protocol IRootCoordinator: AnyObject {
    func navigateToMain()
}

final class RootCoordinator: IRootCoordinator, ObservableObject {
    enum Flow: Hashable {
        case auth(AuthScreen), main, generate(GenerateScreen)

        enum AuthScreen: Hashable {
            case signIn, signUp
        }
        
        enum GenerateScreen: Hashable {
            case randomJoke, settings
        }
    }


    @Published var currentFlow: Flow

    init() {
        self.currentFlow = .auth(.signIn)
    }

    func navigateToMain() {
        currentFlow = .main
    }

    func navigateToAuth(authScreen: Flow.AuthScreen) {
        currentFlow = .auth(authScreen)
    }
    
    func navigateToGenerate(generateScreen: Flow.GenerateScreen) {
        currentFlow = .generate(generateScreen)
    }
}
