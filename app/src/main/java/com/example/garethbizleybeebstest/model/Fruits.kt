package com.example.garethbizleybeebstest.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Fruits(
    @SerializedName("fruit")
    val fruitsList: List<FruitItem>
)

@Parcelize
data class FruitItem(
    @Expose
    val type: String,
    @Expose
    val price: Int,
    @Expose
    val weight: Int
): Parcelable