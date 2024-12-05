package com.srinivas.apiwithtestcasevirtusa.model.datasource

import com.srinivas.apiwithtestcasevirtusa.model.response.ProductsResponse
import retrofit2.Response
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(private val apiService: ApiService) : PostDataSource {
    override suspend fun getProducts(): Response<ProductsResponse> {
        return apiService.getProducts()
    }
}