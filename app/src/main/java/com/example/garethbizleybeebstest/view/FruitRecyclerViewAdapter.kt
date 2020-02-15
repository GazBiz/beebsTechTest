package com.example.garethbizleybeebstest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.garethbizleybeebstest.R
import com.example.garethbizleybeebstest.model.FruitItem

class FruitRecyclerViewAdapter(
    private val fruitsList: List<FruitItem>,
    private val navController: NavController
) : RecyclerView.Adapter<FruitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder =
        FruitViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.fruit_item_row, parent, false
                ),
            navController
        )

    override fun getItemCount(): Int =
        fruitsList.size

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruitItem = fruitsList[position]
        holder.bindFruit(fruitItem)
    }
}