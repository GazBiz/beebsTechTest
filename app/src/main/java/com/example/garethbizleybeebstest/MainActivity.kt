package com.example.garethbizleybeebstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.garethbizleybeebstest.Constants.Companion.ERROR
import com.example.garethbizleybeebstest.logging.LoggingServiceImpl

class MainActivity : AppCompatActivity() {

    private val loggingService = LoggingServiceImpl.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupErrorLogger()
    }

    private fun setupErrorLogger(){
        Thread.setDefaultUncaughtExceptionHandler { _, paramThrowable ->
            loggingService.logEvent(ERROR, paramThrowable.localizedMessage)
                .subscribe()
        }
    }
}
