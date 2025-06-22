package com.itis.joke.core.data.settings

import com.itis.joke.core.common.config.PlatformConfiguration
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings

actual class SettingsFactory {

    actual fun create(
        name: String,
        conf: PlatformConfiguration
    ): Settings = NSUserDefaultsSettings.Factory().create(name = name)
}