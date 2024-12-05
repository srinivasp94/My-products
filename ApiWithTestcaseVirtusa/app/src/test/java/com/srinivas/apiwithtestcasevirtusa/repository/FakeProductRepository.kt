package com.srinivas.apiwithtestcasevirtusa.repository

import com.srinivas.apiwithtestcasevirtusa.model.repository.ProdRepo
import com.srinivas.apiwithtestcasevirtusa.model.response.Products
import com.srinivas.apiwithtestcasevirtusa.model.response.ProductsResponse
import com.srinivas.apiwithtestcasevirtusa.utils.AppConstants
import com.srinivas.apiwithtestcasevirtusa.utils.ResourceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


open class FakeProductRepository  : ProdRepo {

    private val products = listOf(Products(1, "Test", "", "", 10.0, 1.0, 1.0, 10.0f, ""))
    private val prodResponse = ProductsResponse(products, 1, 0, 1)

    override suspend fun getProductsResponse(): Flow<ResourceState> {
        return flow {
            emit(ResourceState.Loading)
            delay(1000)
            emit(ResourceState.Success(prodResponse))
            emit(ResourceState.Error(AppConstants.COMMON_ERROR))
        }
    }
}