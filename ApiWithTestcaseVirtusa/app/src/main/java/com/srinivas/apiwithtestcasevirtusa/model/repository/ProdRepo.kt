package com.srinivas.apiwithtestcasevirtusa.model.repository

import com.srinivas.apiwithtestcasevirtusa.utils.ResourceState
import kotlinx.coroutines.flow.Flow

interface ProdRepo {
    suspend fun getProductsResponse() : Flow<ResourceState>
}