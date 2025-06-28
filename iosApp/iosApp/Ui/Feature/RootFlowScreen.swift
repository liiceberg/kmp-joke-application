//
//  RootView.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct RootFlowScreen: View {
    @StateObject var coordinator = RootCoordinator()

    var body: some View {
        Group {
            switch coordinator.currentFlow {
            case .auth:
                AuthFlowScreen(coordinator: coordinator)
                
            case .generate(.settings):
                JokeSettingsScreen(onBack: coordinator.navigateToMain)
            default:
                MainFlowScreen(coordinator: coordinator)
            }
        }
    }
}
