package com.example.garethbizleybeebstest.repo

import com.example.garethbizleybeebstest.model.Fruits
import io.reactivex.Single

interface FruitRepo {

    fun getFruitData(): Single<Fruits>
}