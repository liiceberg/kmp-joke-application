//
//  GenerateFlowScreen.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct GenerateFlowScreen: View {
    @ObservedObject var coordinator: RootCoordinator

    var body: some View {
        Group {
            switch coordinator.currentFlow {
            case .generate(.randomJoke):
                RandomJokeScreen(toSettings: {
                    coordinator.navigateToGenerate(generateScreen: .settings)
                })
            case .generate(.settings):
                JokeSettingsScreen(onBack: {
                    coordinator.navigateToGenerate(generateScreen: .randomJoke)
                })
            default:
                EmptyView()
            }
        }
    }
}
