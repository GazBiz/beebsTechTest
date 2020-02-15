package com.example.garethbizleybeebstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.garethbizleybeebstest.Constants.Companion.DISPLAY
import com.example.garethbizleybeebstest.Constants.Companion.FRUIT_KEY
import com.example.garethbizleybeebstest.Constants.Companion.START_TIME_KEY
import com.example.garethbizleybeebstest.R
import com.example.garethbizleybeebstest.logging.LoggingServiceImpl
import com.example.garethbizleybeebstest.model.FruitItem
import com.example.garethbizleybeebstest.toPriceDisplay
import com.example.garethbizleybeebstest.toWeightDisplay
import kotlinx.android.synthetic.main.single_fruit_fragment_layout.*

class SingleFruitFragment : Fragment() {

    private val loggingService = LoggingServiceImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
         inflater.inflate(R.layout.single_fruit_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logDisplayTime()
        processArgs()
        setupToolbar()
    }

    private fun setupToolbar(){
        singleFruitToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        singleFruitToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
    }
    
    private fun processArgs(){
        arguments?.getParcelable<FruitItem>(FRUIT_KEY)?.let {fruit ->
            singleFruitToolbar.title = fruit.type.capitalize()
            fruitName.text = fruit.type.capitalize()
            fruitWeight.text = fruit.weight.toWeightDisplay()
            fruitPrice.text = fruit.price.toPriceDisplay()
        }
    }

    private fun logDisplayTime(){
        val startTime = arguments?.getLong(START_TIME_KEY) ?: 0L
        val endTime = System.currentTimeMillis()
        val processTime = (endTime - startTime).toString()
        loggingService.logEvent(DISPLAY, processTime)
    }
}
