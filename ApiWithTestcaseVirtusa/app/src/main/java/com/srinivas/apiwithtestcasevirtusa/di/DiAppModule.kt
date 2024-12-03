package com.srinivas.apiwithtestcasevirtusa.di

import com.srinivas.apiwithtestcasevirtusa.BuildConfig
import com.srinivas.apiwithtestcasevirtusa.model.datasource.ApiService
import com.srinivas.apiwithtestcasevirtusa.model.datasource.PostDataSource
import com.srinivas.apiwithtestcasevirtusa.model.datasource.PostDataSourceImpl
import com.srinivas.apiwithtestcasevirtusa.model.repository.ProdRepo
import com.srinivas.apiwithtestcasevirtusa.model.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiAppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providesGetApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesPostDataSource(apiService: ApiService) : PostDataSource{
        return PostDataSourceImpl(apiService)
    }

    @Provides
    fun providesGetProductRepository(postDataSource: PostDataSource) : ProdRepo{
        return ProductRepository(postDataSource)
    }
}