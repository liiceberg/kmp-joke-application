package com.itis.joke.core.data.settings

import com.itis.joke.core.data.qualifier.QualifierSettingName
import com.russhwolf.settings.Settings
import org.koin.core.qualifier.named
import org.koin.dsl.module

val settingsModule = module {
    single<Settings> {
        SettingsFactory().create(name = get(named<QualifierSettingName>()), get())
    }
}
