package com.example.garethbizleybeebstest.logging

import android.util.Log
import com.example.garethbizleybeebstest.api.LoggingApi
import com.example.garethbizleybeebstest.api.RetrofitService
import io.reactivex.schedulers.Schedulers

class LoggingServiceImpl : LoggingService {

    private val loggingApi = RetrofitService.createService(LoggingApi::class.java)

    override fun logEvent(event: String, data: String) {
        loggingApi.logStats(event, data)
            .doOnComplete {
                Log.d("Log event success", "Event $event data $data")
            }
            .doOnError {
                Log.d("Log event failed", "Event $event data $data")
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}