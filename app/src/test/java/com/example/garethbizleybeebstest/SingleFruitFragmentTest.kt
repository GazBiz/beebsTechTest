package com.example.garethbizleybeebstest

import com.example.garethbizleybeebstest.Constants.Companion.INVALID
import com.example.garethbizleybeebstest.view.toPriceDisplay
import com.example.garethbizleybeebstest.view.toWeightDisplay
import org.junit.Test


class SingleFruitFragmentTest {

    @Test
    fun displayPriceShouldHandleInvalidData(){
        val invalidPrice = -5
        val displayPrice = invalidPrice.toPriceDisplay()
        assert(displayPrice == INVALID)
    }

    @Test
    fun displayWeightShouldHandleInvalidData(){
        val invalidWeight = 0
        val displayWeight = invalidWeight.toWeightDisplay()
        assert(displayWeight == INVALID)
    }

    @Test
    fun displayPriceShouldFormatCorrectly(){
        val validPrice = 120
        val displayPrice = validPrice.toPriceDisplay()
        val expected = "£ 1.20"
        assert(displayPrice == expected)
    }

    @Test
    fun displayWeightShouldFormatCorrectly(){
        val validWeight = 80
        val displayWeight = validWeight.toWeightDisplay()
        val expected = "0.08 kg"
        assert(displayWeight == expected)
    }
}