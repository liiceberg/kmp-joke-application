//
//  FlowCollectorWrapper.swift
//  iosApp
//
//  Created by Мирон Солопов on 27.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//


import Foundation
import CommonKmp

class FlowCollectorWrapper<T>: Kotlinx_coroutines_coreFlowCollector {
    private let onEmit: (T?) -> Void

    init(onEmit: @escaping (T?) -> Void) {
        self.onEmit = onEmit
    }

    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        onEmit(value as? T)
        completionHandler(nil)
    }
}
