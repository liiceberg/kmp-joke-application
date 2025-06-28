//
//  Buttons.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

struct JokeButton: View {
    let title: String
    let isEnabled: Bool
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(title)
                .padding()
                .frame(maxWidth: .infinity)
                .background(Color.primary)
                .foregroundColor(.onPrimary)
                .cornerRadius(8)
        }
        .disabled(!isEnabled)
    }
}
