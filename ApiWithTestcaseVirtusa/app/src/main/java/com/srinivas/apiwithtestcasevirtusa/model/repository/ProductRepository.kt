package com.srinivas.apiwithtestcasevirtusa.model.repository

import com.srinivas.apiwithtestcasevirtusa.model.datasource.PostDataSource
import com.srinivas.apiwithtestcasevirtusa.utils.AppConstants.API_ERROR
import com.srinivas.apiwithtestcasevirtusa.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

 class ProductRepository @Inject constructor(private val postDataSource: PostDataSource) : ProdRepo {

    override suspend fun getProductsResponse(): Flow<ResourceState> {
        return flow {
            emit(ResourceState.Loading)
            val response = postDataSource.getProducts()
            if (response.isSuccessful && response.body() != null)
                emit(ResourceState.Success(response.body()!!))
            else
                emit(ResourceState.Error(API_ERROR))
        }.catch {error ->
            emit(ResourceState.Error(error.printStackTrace().toString()))
        }
    }
}