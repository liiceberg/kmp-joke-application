package com.itis.joke.core.data.qualifier

import org.koin.core.qualifier.named
import org.koin.dsl.module

object QualifierSettingName
object QualifierDatabaseName

val qualifierModule = module {
    factory<String>(qualifier = named<QualifierSettingName>()) {
        "joke_settings"
    }
    factory<String>(qualifier = named<QualifierDatabaseName>()) {
        "joke_database"
    }
}