//
//  SelectableLists.swift
//  iosApp
//
//  Created by Мирон Солопов on 28.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import CommonKmp

struct JokeCategoriesList: View {
    let selectedCategory: JokeCategory
    let onCategoryChange: (JokeCategory) -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("Joke Category")
                .font(.headline)
            
            List {
                ForEach(Array(JokeCategory.entries), id: \.self) { category in
                    HStack {
                        Text(category.value)
                            .foregroundColor(selectedCategory == category ? .accentColor : .primary)
                        Spacer()
                        if selectedCategory == category {
                            Image(systemName: "checkmark")
                                .foregroundColor(.accentColor)
                        }
                    }
                    .contentShape(Rectangle())
                    .onTapGesture {
                        onCategoryChange(category)
                    }
                }
            }
            .listStyle(.plain)
            .frame(height: CGFloat(JokeCategory.entries.count * 44))
        }
    }
}

struct BlacklistList: View {
    let blackList: [JokeBlackListItem]
    let onBlackListChange: (JokeBlackListItem, Bool) -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("Blacklist")
                .font(.headline)
            
            ForEach(Array(JokeBlackListItem.entries), id: \.self) { item in
                Toggle(isOn: Binding(
                    get: { blackList.contains(item) },
                    set: { onBlackListChange(item, $0) }
                )) {
                    Text(item.value.capitalized)
                        .foregroundColor(.primary)
                }
                .toggleStyle(.switch)
                .padding(.vertical, 8)
            }
        }
    }
}
