package com.itis.joke.core.data.database

import app.cash.sqldelight.db.SqlDriver
import com.itis.joke.Database
import com.itis.joke.core.data.qualifier.QualifierDatabaseName
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> {
        DriverFactory().createDriver(
            name = get(named<QualifierDatabaseName>()),
            platformConfiguration = get(),
        )
    }
    single<Database> {
        Database(driver = get())
    }
}