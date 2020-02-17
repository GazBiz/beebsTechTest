package com.example.garethbizleybeebstest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.garethbizleybeebstest.view.FruitListFragment
import com.example.garethbizleybeebstest.view.SingleFruitFragment
import com.example.garethbizleybeebstest.viewmodel.FruitListViewModel
import com.example.garethbizleybeebstest.viewmodel.ViewModelFactory
import com.example.garethbizleybeebstest.viewmodel.ViewModelMapKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ViewModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    fun injectFruitListFragment(): FruitListFragment

    @ContributesAndroidInjector
    fun injectSingleFruitFragment(): SingleFruitFragment

    @Binds
    @IntoMap
    @ViewModelMapKey(FruitListViewModel::class)
    fun bindFruitListViewModel(viewModel: FruitListViewModel): ViewModel
}