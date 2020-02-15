package com.example.garethbizleybeebstest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.garethbizleybeebstest.Constants.Companion.ERROR
import com.example.garethbizleybeebstest.Constants.Companion.LOAD
import com.example.garethbizleybeebstest.api.FruitApi
import com.example.garethbizleybeebstest.api.RetrofitService
import com.example.garethbizleybeebstest.logging.LoggingServiceImpl
import com.example.garethbizleybeebstest.model.FruitItem
import com.example.garethbizleybeebstest.repo.FruitRepoImpl
import io.reactivex.schedulers.Schedulers

class FruitListViewModel: ViewModel() {

    private val fruitRepo =
        FruitRepoImpl(RetrofitService.createService(FruitApi::class.java))

    private val loggingService = LoggingServiceImpl()

    val fruitsList = ArrayList<FruitItem>()
    private val _event = MutableLiveData<FruitEvent>()
    val event: LiveData<FruitEvent> get() = _event

    fun loadFruits(){
        fruitsList.clear()
        val requestStart = System.currentTimeMillis()
        fruitRepo.getFruitData()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                fruitsList.addAll(it.fruitsList)
                _event.postValue(FruitEvent.LOAD_SUCCESS)
                val requestEnd = System.currentTimeMillis()
                val requestTime = (requestEnd - requestStart).toString()

                loggingService.logEvent(LOAD, requestTime)
            }
            .doOnError {
                loggingService.logEvent(ERROR, it.localizedMessage)
            }
            .subscribe()
    }
}

enum class FruitEvent {
    LOAD_SUCCESS
}