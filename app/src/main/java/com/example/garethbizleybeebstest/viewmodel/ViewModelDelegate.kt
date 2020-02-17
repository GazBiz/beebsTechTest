package com.example.garethbizleybeebstest.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import kotlin.reflect.KProperty

class ViewModelDelegate<T : ViewModel>(
    private val type: Class<T>,
    private val factory: () -> ViewModelProvider.Factory? = { null }
) {

    operator fun getValue(reference: Any?, property: KProperty<*>): T =
        when (reference) {
            is FragmentActivity ->
                ViewModelProviders.of(reference, factory())[type]
                    .observing(reference.lifecycle)
            is Fragment ->
                ViewModelProviders.of(reference, factory())[type]
                    .observing(reference.lifecycle)
            else ->
                throw IllegalArgumentException(
                    "ViewModel must be provided inside a Fragment."
                )
        }

    private fun T.observing(lifecycle: Lifecycle): T {
        if (this is LifecycleObserver) {
            lifecycle.addObserver(this)
        }
        return this
    }
}

inline fun <reified T : ViewModel> viewModel(
    noinline factory: () -> ViewModelProvider.Factory?
): ViewModelDelegate<T> =
    ViewModelDelegate(T::class.java, factory)
