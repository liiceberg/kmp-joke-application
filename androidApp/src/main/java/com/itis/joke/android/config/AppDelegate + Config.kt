package com.itis.joke.android.config

import android.os.Build
import com.itis.joke.core.common.CommonKmp
import com.itis.joke.android.AppDelegate
import com.itis.joke.android.BuildConfig
import com.itis.joke.core.common.config.Configuration
import com.itis.joke.core.common.config.PlatformConfiguration

internal fun AppDelegate.initCommon() {
    val config = Configuration(
        platformConfiguration = PlatformConfiguration(
            androidContext = applicationContext,
            appVersionName = BuildConfig.VERSION_NAME,
            appVersionNumber = BuildConfig.VERSION_CODE.toString(),
            osVersion = Build.VERSION.RELEASE.toString(),
        ),
        isDebug = BuildConfig.DEBUG,
        isHttpLoggingEnabled = BuildConfig.DEBUG,
    )

    CommonKmp.initKoin(config)

}
