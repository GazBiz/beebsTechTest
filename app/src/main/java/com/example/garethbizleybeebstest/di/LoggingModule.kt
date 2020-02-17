package com.example.garethbizleybeebstest.di

import com.example.garethbizleybeebstest.logging.LoggingService
import com.example.garethbizleybeebstest.logging.LoggingServiceImpl
import dagger.Binds
import dagger.Module

@Module
interface LoggingModule {

    @Binds
    fun bindLoggingService(loggingService: LoggingServiceImpl): LoggingService
}