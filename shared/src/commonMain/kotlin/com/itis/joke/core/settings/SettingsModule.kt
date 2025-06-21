package com.itis.joke.core.settings

import com.itis.joke.core.qualifier.QualifierSettingName
import com.russhwolf.settings.Settings
import org.koin.core.qualifier.named
import org.koin.dsl.module

val storageModule = module {
    single<Settings> {
        SettingsFactory().create(name = get(named<QualifierSettingName>()), get())
    }
}
