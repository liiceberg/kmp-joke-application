//
//  JokeSettingsView.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import Foundation
import Combine
import SwiftUI
import CommonKmp

struct JokeSettingsScreen: View {
    @StateObject private var viewModelWrapper = JokeSettingsViewModelWrapper()
    var onBack: () -> Void
    
    var body: some View {
        JokeSettingsView(
            state: viewModelWrapper.state,
            onSave: {
                viewModelWrapper.viewModel.obtainEvent(event: JokeSettingsEventOnSave())
            },
            onCategoryChange: { category in
                viewModelWrapper.viewModel.obtainEvent(event: JokeSettingsEventOnCategoryChange(category: category))
            },
            onJokeTypeChange: { type in
                viewModelWrapper.viewModel.obtainEvent(event: JokeSettingsEventOnJokeTypeChange(jokeType: type))
            },
            onBlackListChange: { item, checked in
                viewModelWrapper.viewModel.obtainEvent(event: JokeSettingsEventOnBlackListChange(item: item, checked: checked))
            },
            onBack: onBack
        )
        .task {
            await viewModelWrapper.collectStateFlow()
        }
    }
    
    private func showToast(message: String) {
    }
}

struct JokeSettingsView: View {
    let state: JokeSettingsState
    let onSave: () -> Void
    let onCategoryChange: (JokeCategory) -> Void
    let onJokeTypeChange: (JokeType) -> Void
    let onBlackListChange: (JokeBlackListItem, Bool) -> Void
    let onBack: () -> Void
    
    var body: some View {
        VStack {

            HStack {
                Button(action: onBack) {
                    Image(systemName: "arrow.backward")
                        .frame(width: 24, height: 24)
                }
                .padding(.leading, 16)
                
                Spacer()
            }
            .padding(.top, 16)
            
            ScrollView {
                VStack(spacing: 20) {
                    JokeCategoriesList(
                        selectedCategory: state.category,
                        onCategoryChange: onCategoryChange
                    )
                    
                    VStack(alignment: .leading, spacing: 8) {
                        Text(String(localized: "joke_type"))
                            .font(.headline)
                        
                        Picker(String(localized: "joke_type"), selection: Binding(
                            get: { state.type },
                            set: { onJokeTypeChange($0) }
                        )) {
                            ForEach(JokeType.entries, id: \.self) { type in
                                Text(type.name.capitalized)
                                    .tag(type)
                            }
                        }
                        .pickerStyle(.segmented)
                    }
                    
                    BlacklistList(
                        blackList: state.blackList,
                        onBlackListChange: onBlackListChange
                    )
                    
                    Spacer()
                    
                    JokeButton(title: String(localized: "save"), isEnabled: true, action: onSave)
                }
                .padding(.horizontal, 16)
                .padding(.top, 16)
                .padding(.bottom, 20)
            }
        }
    }
}
