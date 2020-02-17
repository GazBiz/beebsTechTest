package com.example.garethbizleybeebstest.logging

import android.util.Log
import com.example.garethbizleybeebstest.api.LoggingApi
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoggingServiceImpl @Inject constructor(
    private val loggingApi: LoggingApi
) : LoggingService {

    override fun logEvent(event: String, data: String): Completable =
        loggingApi.logEvent(event, data)
            .doOnComplete {
                Log.d("Log event success", "Event $event data $data")
            }
            .doOnError {
                Log.d("Log event failed", "Event $event data $data")
            }
            .subscribeOn(Schedulers.io())
}