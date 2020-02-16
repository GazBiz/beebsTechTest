package com.example.garethbizleybeebstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.garethbizleybeebstest.Constants
import com.example.garethbizleybeebstest.Constants.Companion.DISPLAY
import com.example.garethbizleybeebstest.Constants.Companion.FRUIT_KEY
import com.example.garethbizleybeebstest.Constants.Companion.START_TIME_KEY
import com.example.garethbizleybeebstest.R
import com.example.garethbizleybeebstest.model.FruitItem
import kotlinx.android.synthetic.main.single_fruit_fragment_layout.*

class SingleFruitFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.single_fruit_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logDisplayTime()
        getArgs()
        setupToolbar()
    }

    private fun setupToolbar() {
        singleFruitToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        singleFruitToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
    }

    private fun getArgs() {
        arguments?.getParcelable<FruitItem>(FRUIT_KEY)?.let { fruit ->
            singleFruitToolbar.title = fruit.type.capitalize()
            fruitName.text = fruit.type.capitalize()
            fruitWeight.text = fruit.weight.toWeightDisplay()
            fruitPrice.text = fruit.price.toPriceDisplay()
        }
    }

    private fun logDisplayTime() {
        arguments?.getLong(START_TIME_KEY)?.let { startTime ->
            val endTime = System.currentTimeMillis()
            val displayTime = (endTime - startTime).toString()

            logEvent(DISPLAY, displayTime)
        }
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
                .toBigDecimal()
                .setScale(2)
                .toString()
        )