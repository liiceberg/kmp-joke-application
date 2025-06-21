package com.itis.joke.core.qualifier

import org.koin.core.qualifier.named
import org.koin.dsl.module

object QualifierSettingName

val qualifierModule = module {
    factory<String>(qualifier = named<QualifierSettingName>()) {
        "joke_settings"
    }
}