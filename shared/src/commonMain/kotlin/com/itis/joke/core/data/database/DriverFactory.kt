package com.itis.joke.core.data.database

import app.cash.sqldelight.db.SqlDriver
import com.itis.joke.core.common.config.PlatformConfiguration

internal expect class DriverFactory() {

    fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver
}