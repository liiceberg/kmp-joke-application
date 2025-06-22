package com.itis.joke.core.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.itis.joke.Database
import com.itis.joke.core.common.config.PlatformConfiguration

actual class DriverFactory actual constructor() {
    actual fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration
    ): SqlDriver = AndroidSqliteDriver(
        name = name,
        schema = Database.Schema,
        context = platformConfiguration.androidContext,
    )
}