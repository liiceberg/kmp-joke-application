//
//  AuthFlowView.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AuthFlowScreen: View {
    @ObservedObject var coordinator: RootCoordinator

    var body: some View {
        Group {
            switch coordinator.currentFlow {
            case .auth(.signIn):
                SignInScreen(toSignUp: {
                    coordinator.navigateToAuth(authScreen: .signUp)
                }, toMainPage: {
                    coordinator.navigateToMain()
                })
            case .auth(.signUp):
                SignUpScreen(toSignIn: {
                    coordinator.navigateToAuth(authScreen: .signIn)
                })
            default:
                EmptyView()
            }
        }
    }
}
