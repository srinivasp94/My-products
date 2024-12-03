package com.srinivas.apiwithtestcasevirtusa.views.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import com.srinivas.apiwithtestcasevirtusa.R
import com.srinivas.apiwithtestcasevirtusa.databinding.ActivityMainBinding
import com.srinivas.apiwithtestcasevirtusa.model.response.Products
import com.srinivas.apiwithtestcasevirtusa.model.response.ProductsResponse
import com.srinivas.apiwithtestcasevirtusa.utils.AppConstants
import com.srinivas.apiwithtestcasevirtusa.utils.ResourceState
import com.srinivas.apiwithtestcasevirtusa.viewmodels.ProductViewModel
import com.srinivas.apiwithtestcasevirtusa.views.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ProductsAdapter()
        binding.productList.adapter = adapter
        // Observe the userFlow and update the UI
        lifecycleScope.launch {
            viewModel.productResponse.collect { response ->
               when(response){
                   is ResourceState.Loading -> {
                       binding.loader.visibility = View.VISIBLE
                       binding.productList.visibility = View.GONE
                       binding.textNoProducts.visibility = View.GONE
                   }
                   is ResourceState.Error -> {
                       val errorMessage = (response).error
                       binding.loader.visibility = View.GONE
                       binding.productList.visibility = View.GONE
                       binding.textNoProducts.visibility = View.VISIBLE
                       binding.textNoProducts.text = errorMessage
                   }
                   is ResourceState.Success -> {
                       "srinivas".findLength()
                       binding.loader.visibility = View.GONE
                       binding.productList.visibility = View.VISIBLE
                       binding.textNoProducts.visibility = View.GONE
                       val list = (response).data.products
                       adapter.submitList(list as ArrayList<Products>)
                   }
               }
            }
        }

        viewModel.getProducts()
    }

    fun String.findLength() : String{
        return ""+this.length
    }
}