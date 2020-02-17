package com.example.garethbizleybeebstest.di

import com.example.garethbizleybeebstest.repo.FruitRepo
import com.example.garethbizleybeebstest.repo.FruitRepoImpl
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    fun bindFruitRepo(repo: FruitRepoImpl): FruitRepo
}