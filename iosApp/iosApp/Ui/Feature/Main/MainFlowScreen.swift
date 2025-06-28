//
//  MainFlowScreen.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct MainFlowScreen: View {
    @ObservedObject var coordinator: RootCoordinator

       var body: some View {
           TabView {
               RandomJokeScreen(toSettings: {
                   coordinator.navigateToGenerate(generateScreen: .settings)
               })
                   .tabItem {
                       Label("", systemImage: "dice")
                   }


               LibraryScreen()
                   .tabItem {
                       Label("", systemImage: "tray.full.fill")
                   }
               
               SuggestJokeScreen()
                   .tabItem {
                       Label("", systemImage: "lightbulb.max")
                   }
           }
           
       }
}
