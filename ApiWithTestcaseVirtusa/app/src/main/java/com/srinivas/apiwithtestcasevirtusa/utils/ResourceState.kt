package com.srinivas.apiwithtestcasevirtusa.utils

import com.srinivas.apiwithtestcasevirtusa.model.response.ProductsResponse


sealed class ResourceState {
    data object Loading: ResourceState()
    data class Success(val data : ProductsResponse ) : ResourceState()
    data class Error(val error : String) : ResourceState()
}