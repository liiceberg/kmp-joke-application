package com.itis.joke.android

import android.app.Application
import com.itis.joke.android.config.initCommon

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()

        initCommon()
    }
}