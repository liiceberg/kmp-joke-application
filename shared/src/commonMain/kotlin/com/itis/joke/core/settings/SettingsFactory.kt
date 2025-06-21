package com.itis.joke.core.settings

import com.itis.joke.core.config.PlatformConfiguration
import com.russhwolf.settings.Settings

expect class SettingsFactory() {

    fun create(name: String, conf: PlatformConfiguration): Settings
}