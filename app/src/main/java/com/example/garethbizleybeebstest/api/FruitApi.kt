package com.example.garethbizleybeebstest.api

import com.example.garethbizleybeebstest.model.Fruits
import io.reactivex.Single
import retrofit2.http.GET

interface FruitApi {

    @GET("fmtvp/recruit-test-data/master/data.json")
    fun getFruitData(): Single<Fruits>
}