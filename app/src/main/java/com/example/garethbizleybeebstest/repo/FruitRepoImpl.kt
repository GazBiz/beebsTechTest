package com.example.garethbizleybeebstest.repo

import com.example.garethbizleybeebstest.api.FruitApi
import com.example.garethbizleybeebstest.model.Fruits
import io.reactivex.Single

class FruitRepoImpl(
    private val fruitApi: FruitApi
): FruitRepo {

    override fun getFruitData(): Single<Fruits> =
        fruitApi.getFruitData()
}