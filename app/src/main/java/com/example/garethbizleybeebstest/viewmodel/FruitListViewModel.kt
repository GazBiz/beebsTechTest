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

    private val disposables = CompositeDisposable()

    private val loggingService = LoggingServiceImpl.instance

    val fruitsList = ArrayList<FruitItem>()
    private val _event = MutableLiveData<FruitEvent>()
    val event: LiveData<FruitEvent> get() = _event

    fun loadFruits() {
        val requestStart = System.currentTimeMillis()

        val requestDisposable =
            fruitRepo.getFruitData()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                logRequestDuration(requestStart)
                updateViewData(it.fruitsList)
            }
            .doOnError {
                val errorDisposable = loggingService.logEvent(ERROR, it.localizedMessage)
                    .subscribe()
                disposables.add(errorDisposable)
            }
            .subscribe()
        disposables.add(requestDisposable)
    }

    private fun logRequestDuration(requestStart: Long) {
        val requestEnd = System.currentTimeMillis()
        val requestDuration = (requestEnd - requestStart).toString()
        val loadingDisposable = loggingService.logEvent(LOAD, requestDuration)
            .subscribe()
        disposables.add(loadingDisposable)
    }

    private fun updateViewData(fruits: List<FruitItem>) {
        fruitsList.clear()
        fruitsList.addAll(fruits)
        _event.postValue(FruitEvent.LOAD_SUCCESS)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}

enum class FruitEvent {
    LOAD_SUCCESS
}