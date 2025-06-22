package com.itis.joke.core.data.settings

import com.itis.joke.core.common.config.PlatformConfiguration
import com.russhwolf.settings.Settings

expect class SettingsFactory() {

    fun create(name: String, conf: PlatformConfiguration): Settings
}