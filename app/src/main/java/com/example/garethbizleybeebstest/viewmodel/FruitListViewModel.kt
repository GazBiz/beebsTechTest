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
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FruitListViewModel : ViewModel() {

    private val fruitRepo =
        FruitRepoImpl(RetrofitService.createService(FruitApi::class.java))

    val fruitsList = ArrayList<FruitItem>()
    private val _event = MutableLiveData<FruitEvent>()
    val event: LiveData<FruitEvent> get() = _event

    fun loadFruits() {
        val requestStart = System.currentTimeMillis()

        fruitRepo.getFruitData()
            .subscribeOn(Schedulers.io())
            .doOnSuccess { fruits ->
                logRequestDuration(requestStart)
                updateViewData(fruits.fruitsList)
            }
            .doOnError { throwable ->
                _event.postValue(FruitEvent.LogEvent(ERROR, throwable.localizedMessage))
            }
            .subscribe()
    }

    private fun logRequestDuration(requestStart: Long) {
        val requestEnd = System.currentTimeMillis()
        val requestDuration = (requestEnd - requestStart).toString()
        _event.postValue(FruitEvent.LogEvent(LOAD, requestDuration))
    }

    private fun updateViewData(fruits: List<FruitItem>) {
        fruitsList.clear()
        fruitsList.addAll(fruits)
        _event.postValue(FruitEvent.LoadSuccess)
    }
}

sealed class FruitEvent {
    object LoadSuccess : FruitEvent()
    class LogEvent(val event: String, val data: String) : FruitEvent()
}