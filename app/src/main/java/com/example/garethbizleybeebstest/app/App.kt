package com.example.garethbizleybeebstest.app

import android.app.Application
import com.example.garethbizleybeebstest.Constants
import com.example.garethbizleybeebstest.di.DaggerAppInjector
import com.example.garethbizleybeebstest.logging.LoggingService
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject
    lateinit var loggingService: LoggingService

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        setupErrorLogger()

        DaggerAppInjector.builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun setupErrorLogger(){
        Thread.setDefaultUncaughtExceptionHandler { _, paramThrowable ->
            loggingService.logEvent(Constants.ERROR, paramThrowable.localizedMessage)
                .subscribe()
        }
    }
}