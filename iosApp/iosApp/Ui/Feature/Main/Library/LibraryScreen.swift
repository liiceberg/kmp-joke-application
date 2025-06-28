//
//  LibraryView.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Combine
import CommonKmp

struct LibraryScreen: View {
    @StateObject private var viewModelWrapper = LibraryViewModelWrapper()
    
    var body: some View {
        JokeLibraryView(
            state: viewModelWrapper.state,
            onSearchFilled: { query in
                viewModelWrapper.viewModel.obtainEvent(
                    event: JokeLibraryEventOnSearchQueryChange(query: query)
                    )
            }
        )
        .task {
            await viewModelWrapper.collectStateFlow()
        }
    }
}

struct JokeLibraryView: View {
    var state: JokeLibraryState
    var onSearchFilled: (String) -> Void
    
    var body: some View {
        VStack {
            JokeTextField(
                placeholder: "search",
                text: Binding(
                    get: { state.query },
                    set: { onSearchFilled($0) }
                ),
                onCommit: {}
            )
            .padding(.horizontal, 16)
            .padding(.vertical, 32)
            
            ZStack {
                switch state.loadState {
                case is LoadStateLoading:
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle())
                    
                case let errorState as LoadStateError:
                    ErrorMessage(errorText: errorState.message)
                    
                default:
                    JokeList(items: state.items)
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

struct JokeList: View {
    var items: [String]
    
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 0) {
                ForEach(items.indices, id: \.self) { index in
                    JokeItem(joke: items[index])
                    
                    if index < items.count - 1 {
                        Divider()
                            .frame(height: 1)
                            .overlay(Color.primary)
                            .padding(.horizontal, 24)
                    }
                }
            }
            .background(Color.primaryContainer)
        }
    }
}

struct JokeItem: View {
    var joke: String
    
    var body: some View {
        Text(joke)
            .font(.title3)
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(16)
            .foregroundColor(.onPrimaryContainer)
    }
}

struct ErrorMessage: View {
    var errorText: String
    
    var body: some View {
        Text(errorText)
            .padding()
            .background(Color.red.opacity(0.8))
            .foregroundColor(.white)
            .cornerRadius(8)
    }
}
