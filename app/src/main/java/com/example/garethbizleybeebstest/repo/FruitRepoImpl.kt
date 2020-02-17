package com.example.garethbizleybeebstest.repo

import com.example.garethbizleybeebstest.api.FruitApi
import com.example.garethbizleybeebstest.model.Fruits
import io.reactivex.Single
import javax.inject.Inject

class FruitRepoImpl @Inject constructor(
    private val fruitApi: FruitApi
): FruitRepo {

    override fun getFruitData(): Single<Fruits> =
        fruitApi.getFruitData()
}