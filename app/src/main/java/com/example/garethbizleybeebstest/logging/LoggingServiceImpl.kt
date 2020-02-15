package com.example.garethbizleybeebstest.logging

import android.util.Log
import com.example.garethbizleybeebstest.api.LoggingApi
import com.example.garethbizleybeebstest.api.RetrofitService
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class LoggingServiceImpl private constructor() : LoggingService {

    private val loggingApi = RetrofitService.createService(LoggingApi::class.java)

    private object HOLDER {
        val INSTANCE = LoggingServiceImpl()
    }

    companion object {
        val instance: LoggingServiceImpl by lazy { HOLDER.INSTANCE }
    }

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