package com.example.garethbizleybeebstest.view

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.garethbizleybeebstest.logging.LoggingService
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @Inject
    lateinit var loggingService : LoggingService

    private val disposables = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context.applicationContext is HasAndroidInjector) {
            AndroidSupportInjection.inject(this)
        }
    }

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