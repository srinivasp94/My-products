package com.srinivas.apiwithtestcasevirtusa.di

import com.srinivas.apiwithtestcasevirtusa.model.repository.ProdRepo
import com.srinivas.apiwithtestcasevirtusa.repository.FakeProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DiAppModule::class]
)*/


@Module
@InstallIn(SingletonComponent::class)
object TestDiAppModule {

    @Provides
    @Singleton
    fun provideFakeProductRepository() : ProdRepo{
        return FakeProductRepository()
    }
}