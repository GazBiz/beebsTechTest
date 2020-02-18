package com.example.garethbizleybeebstest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.garethbizleybeebstest.Constants
import com.example.garethbizleybeebstest.model.FruitItem
import javax.inject.Inject

class SingleFruitViewModel @Inject constructor(): ViewModel() {

    private val _fruit = MutableLiveData<FruitItem>()

    val fruitName: LiveData<String> get() = _fruit.map {
        it.type.capitalize()
    }

    val price: LiveData<String> get() = _fruit.map {
        it.price.toPriceDisplay()
    }

    val weight: LiveData<String> get() = _fruit.map {
        it.weight.toWeightDisplay()
    }

    fun setViewData(fruitItem: FruitItem){
        _fruit.value = (fruitItem)
    }


}

/**
 * Returns a `LiveData` containing the results of applying the given [transform] function
 * to the value in the original `LiveData`.
 */
fun <T, R> LiveData<T>.map(transform: (T) -> R?): LiveData<R> =
    Transformations.map(this, transform)

fun Int?.toWeightDisplay(): String =
    if (this == null || this <= 0)
        Constants.INVALID
    else
        this.toDouble()
            .div(Constants.GRAMS_IN_KILO)
            .toString()
            .plus(Constants.KILO_SUFFIX)

fun Int?.toPriceDisplay(): String =
    if (this == null || this <= 0)
        Constants.INVALID
    else
        Constants.POUND_PREFIX.plus(
            this.toDouble()
                .div(Constants.PENCE_IN_POUND)
                .toBigDecimal()
                .setScale(2)
                .toString()
        )