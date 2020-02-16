package com.example.garethbizleybeebstest

import android.app.Application
import com.example.garethbizleybeebstest.logging.LoggingServiceImpl

class App: Application() {

    private val loggingService = LoggingServiceImpl.instance

    override fun onCreate() {
        super.onCreate()
        setupErrorLogger()
    }

    private fun setupErrorLogger(){
        Thread.setDefaultUncaughtExceptionHandler { _, paramThrowable ->
            loggingService.logEvent(Constants.ERROR, paramThrowable.localizedMessage)
                .subscribe()
        }
    }
}