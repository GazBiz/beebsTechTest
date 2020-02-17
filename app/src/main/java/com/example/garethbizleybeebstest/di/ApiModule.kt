package com.example.garethbizleybeebstest.di

import com.example.garethbizleybeebstest.Constants.Companion.BASE_URL
import com.example.garethbizleybeebstest.api.FruitApi
import com.example.garethbizleybeebstest.api.LoggingApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    fun provideFruitApi(retrofit: Retrofit): FruitApi =
        retrofit.create(FruitApi::class.java)

    @Provides
    fun provideLoggingApi(retrofit: Retrofit): LoggingApi =
        retrofit.create(LoggingApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}