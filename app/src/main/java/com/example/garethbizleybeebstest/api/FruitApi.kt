package com.example.garethbizleybeebstest.api

import com.example.garethbizleybeebstest.model.Fruits
import io.reactivex.Single
import retrofit2.http.GET

interface FruitApi {

    @GET("data.json")
    fun getFruitData(): Single<Fruits>
}