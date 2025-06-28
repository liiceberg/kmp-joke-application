//
//  SuggestView.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Combine
import CommonKmp

struct SuggestJokeScreen: View {
    @StateObject private var viewModelWrapper = SuggestJokeViewModelWrapper()
    
    var body: some View {
        SuggestJokeView(
            state: viewModelWrapper.state,
            onJokeChanged: { joke in
                viewModelWrapper.viewModel.obtainEvent(
                    event: SuggestJokeEventOnJokeFilled(joke: joke)
                )
            },
            onAdditionalPartChanged: { additionalPart in
                viewModelWrapper.viewModel.obtainEvent(
                    event: SuggestJokeEventOnJokeAdditionalPartFilled(joke: additionalPart)
                )
            },
            onSubmit: {
                viewModelWrapper.viewModel.obtainEvent(
                    event: SuggestJokeEventOnSubmit()
                )
            },
            onCategoryChange: { category in
                viewModelWrapper.viewModel.obtainEvent(
                    event: SuggestJokeEventOnCategoryChange(category: category)
                )
            },
            onBlackListChange: { item, checked in
                viewModelWrapper.viewModel.obtainEvent(
                    event: SuggestJokeEventOnBlackListChange(item: item, checked: checked)
                )
            }
        
        )
        .task {
            await viewModelWrapper.collectStateFlow()
        }
    }
}

struct SuggestJokeView: View {
    var state: SuggestJokeState
    var onJokeChanged: (String) -> Void
    var onAdditionalPartChanged: (String) -> Void
    var onSubmit: () -> Void
    var onCategoryChange: (JokeCategory) -> Void
    var onBlackListChange: (JokeBlackListItem, Bool) -> Void
    
    var body: some View {
        ZStack {
            Color.background.edgesIgnoringSafeArea(.all)
            
            VStack(spacing: 0) {
                ScrollView {
                    VStack(spacing: 16) {
                        JokeTextField(
                            placeholder: String(localized: "your_joke"),
                            text: Binding(
                                get: { state.joke },
                                set: { onJokeChanged($0) }
                            ),
                            onCommit: {}
                        )
                        
                        JokeTextField(
                            placeholder: String(localized: "additional_part"),
                            text: Binding(
                                get: { state.jokeAdditionalPart },
                                set: { onAdditionalPartChanged($0) }
                            ),
                            onCommit: {}
                        )
                        
                        Spacer().frame(height: 8)
                        
                        JokeCategoriesList(
                            selectedCategory: state.category,
                            onCategoryChange: onCategoryChange
                        )
                        
                        Spacer().frame(height: 8)
                        
                        BlacklistList(
                            blackList: state.blackList,
                            onBlackListChange: onBlackListChange
                        )
                    }
                    .padding(.horizontal, 16)
                    .padding(.top, 32)
                }
                
                JokeButton(
                    title: String(localized: "submit"),
                    isEnabled: !state.joke.isEmpty && !(state.loadState is LoadStateLoading),
                    action: onSubmit
                )
                .padding(.horizontal, 16)
                .padding(.bottom, 16)
            }
            
            if state.loadState is LoadStateLoading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle())
            }
        }
    }
}
