//
//  GenerateView.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Combine
import CommonKmp

struct RandomJokeScreen: View {
    @StateObject private var viewModelWrapper = RandomJokeViewModelWrapper()
    var toSettings: () -> Void
    
    var body: some View {
        RandomJokeView(
            state: viewModelWrapper.state,
            onGenerateClick: {
                viewModelWrapper.viewModel.obtainEvent(event: RandomJokeEventOnGenerateClick())
            },
            onSettingsClick: toSettings
        )
        .task {
            await viewModelWrapper.collectStateFlow()
        }
    }
}

struct RandomJokeView: View {
    var state: RandomJokeState
    var onGenerateClick: () -> Void
    var onSettingsClick: () -> Void
    
    var body: some View {
        ZStack {
            Color.background.edgesIgnoringSafeArea(.all)
            
            VStack {
                HStack {
                    Spacer()
                    Button(
                        action: {
                        onSettingsClick()
                    }) {
                        Image(systemName: "gear")
                            .frame(width: 56, height: 56)
                            .foregroundColor(.primary)
                    }
                    .padding(.trailing, 16)
                }
                
                Spacer().frame(height: 16)
                
                if let joke = state.joke {
                    switch joke.type {
                    case .single:
                        let singleJoke = joke as! SingleJokeModel
                        JokeCard(text: singleJoke.joke())
                        
                    case .twopart:
                        let twoPartJoke = joke as! TwoPartJokeModel
                        VStack {
                            JokeCard(text: twoPartJoke.setup)
                            Spacer().frame(height: 16)
                            JokeCard(
                                text: twoPartJoke.delivery,
                                cardColor: .tertiary,
                                textColor: .onTertiary
                            )
                        }
                        
                    default:
                        EmptyView()
                    }
                }
                
                Spacer()
                
                Button(action: onGenerateClick) {
                    HStack {
                        Text(String(localized:"generate_joke"))
                            .padding(8)
                        
                        if state.loadState is LoadStateLoading {
                            ProgressView()
                                .progressViewStyle(CircularProgressViewStyle())
                                .foregroundColor(.onPrimary)
                        } else {
                            Image(systemName: "sparkles")
                                .frame(width: 24, height: 24)
                        }
                    }
                }
                .padding(.bottom, 48)
            }
            .padding(.horizontal, 24)
        }
    }
}

struct JokeCard: View {
    var text: String
    var cardColor: Color = .primary
    var textColor: Color = .onPrimary
    
    var body: some View {
        Text(text)
            .padding()
            .frame(maxWidth: .infinity)
            .background(cardColor)
            .foregroundColor(textColor)
            .cornerRadius(8)
    }
}
