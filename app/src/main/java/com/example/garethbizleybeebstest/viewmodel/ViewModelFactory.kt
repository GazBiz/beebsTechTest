package com.example.garethbizleybeebstest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(type: Class<T>): T {
        val viewModel = viewModels[type]?.get()

        checkNotNull(viewModel) { "Error creating ${type.simpleName}."}

        check(type.isInstance(viewModel)) { "Error creating ${type.simpleName}." }

        return requireNotNull(type.cast(viewModel))
    }
}