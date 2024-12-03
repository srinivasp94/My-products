package com.srinivas.apiwithtestcasevirtusa.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srinivas.apiwithtestcasevirtusa.model.repository.ProdRepo
import com.srinivas.apiwithtestcasevirtusa.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val prodRepo: ProdRepo
) : ViewModel() {

    private val _productResponse : MutableStateFlow<ResourceState> = MutableStateFlow(ResourceState.Loading)
    val productResponse : StateFlow<ResourceState> = _productResponse

     fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            prodRepo.getProductsResponse().collectLatest {response : ResourceState ->
                _productResponse.value = response
            }
        }
    }
}