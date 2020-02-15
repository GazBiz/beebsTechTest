package com.example.garethbizleybeebstest.view

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.garethbizleybeebstest.Constants.Companion.FRUIT_KEY
import com.example.garethbizleybeebstest.Constants.Companion.START_TIME_KEY
import com.example.garethbizleybeebstest.R
import com.example.garethbizleybeebstest.model.FruitItem
import kotlinx.android.synthetic.main.fruit_item_row.view.*

class FruitViewHolder(
    private val view: View,
    private val navController: NavController
) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    private var fruitItem: FruitItem? = null

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val startTime = System.currentTimeMillis()
        val bundle = bundleOf(
            FRUIT_KEY to fruitItem,
            START_TIME_KEY to startTime
        )
        navController.navigate(R.id.actionShowSingleFruitFragment, bundle)
    }

    fun bindFruit(fruit: FruitItem) {
        this.fruitItem = fruit
        view.fruitName.text = fruitItem?.type?.capitalize()
    }
}