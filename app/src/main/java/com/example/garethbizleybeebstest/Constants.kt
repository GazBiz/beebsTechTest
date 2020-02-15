package com.example.garethbizleybeebstest

class Constants {

    companion object {
        const val FRUIT_KEY = "fruit"
        const val GRAMS_IN_KILO = 1000
        const val KILO_SUFFIX = " kg"
        const val INVALID = "Invalid value"
        const val POUND_PREFIX = "£ "
        const val PENCE_IN_POUND = 100
        const val ERROR = "error"
        const val LOAD = "load"
        const val DISPLAY = "display"
        const val START_TIME_KEY = "startTime"
    }
}

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
                .toString()
        )
