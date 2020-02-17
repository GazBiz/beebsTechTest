package com.example.garethbizleybeebstest.di

import dagger.Module

@Module(
    includes = [
    ViewModule::class,
    RepoModule::class,
    ApiModule::class,
    LoggingModule::class
    ]
)
class AppModule