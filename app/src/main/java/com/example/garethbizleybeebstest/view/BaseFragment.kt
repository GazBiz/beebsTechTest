package com.example.garethbizleybeebstest.view

import androidx.fragment.app.Fragment
import com.example.garethbizleybeebstest.logging.LoggingServiceImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseFragment: Fragment() {

    private val loggingService = LoggingServiceImpl.instance

    private val disposables = CompositeDisposable()

    fun logEvent(event: String, data: String) {
        val disposable = loggingService.logEvent(event, data)
            .subscribeOn(Schedulers.io())
            .subscribe()
        disposables.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        disposables.dispose()
    }
}