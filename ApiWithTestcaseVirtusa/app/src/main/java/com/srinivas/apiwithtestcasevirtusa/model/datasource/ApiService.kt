package com.srinivas.apiwithtestcasevirtusa.model.datasource

import com.srinivas.apiwithtestcasevirtusa.model.response.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/products")
    suspend fun getProducts() : Response<ProductsResponse>
}