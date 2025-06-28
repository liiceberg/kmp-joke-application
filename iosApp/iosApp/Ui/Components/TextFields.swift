//
//  TextFields.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct JokeTextField: View {
    let placeholder: String
    @Binding var text: String
    let onCommit: (() -> Void)?
    var autocapitalization: UITextAutocapitalizationType = .sentences
    
    var body: some View {
        TextField(placeholder, text: $text, onCommit: {
            onCommit?()
        })
        .autocapitalization(autocapitalization)
        .textFieldStyle(RoundedBorderTextFieldStyle())
    }
}
