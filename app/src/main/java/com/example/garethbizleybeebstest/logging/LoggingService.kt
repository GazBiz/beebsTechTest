package com.example.garethbizleybeebstest.logging

import io.reactivex.Completable

interface LoggingService {

    fun logEvent(event: String, data: String): Completable

}