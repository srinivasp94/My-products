package com.srinivas.apiwithtestcasevirtusa.model.datasource

import com.srinivas.apiwithtestcasevirtusa.model.response.ProductsResponse
import retrofit2.Response

interface PostDataSource {
    suspend fun getProducts() : Response<ProductsResponse>
}