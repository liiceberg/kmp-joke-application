//
//  AppDelegate.swift
//  iosApp
//
//  Created by Айсылу Гималетдинова on 6/21/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import FirebaseCore

class AppDelegate: NSObject, UIApplicationDelegate {
  func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
    FirebaseApp.configure()
    return true
  }
}
