package com.example.garethbizleybeebstest.api

import io.reactivex.Completable
import retrofit2.http.GET
import retrofit2.http.Query

interface LoggingApi {

    @GET("stats")
    fun logStats(
        @Query("event") event: String,
        @Query("data") data: String
    ): Completable
}