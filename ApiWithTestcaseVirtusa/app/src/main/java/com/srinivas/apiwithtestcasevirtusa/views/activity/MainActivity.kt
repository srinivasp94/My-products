package com.srinivas.apiwithtestcasevirtusa.views.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.srinivas.apiwithtestcasevirtusa.R
import com.srinivas.apiwithtestcasevirtusa.databinding.ActivityMainBinding
import com.srinivas.apiwithtestcasevirtusa.model.response.Products
import com.srinivas.apiwithtestcasevirtusa.utils.ResourceState
import com.srinivas.apiwithtestcasevirtusa.viewmodels.ProductViewModel
import com.srinivas.apiwithtestcasevirtusa.views.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.simpleName.toString()
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ProductsAdapter()
        binding.productList.adapter = adapter

        // Observe the Flow and update the UI
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
                       if (list.isEmpty()){
                           binding.textNoProducts.visibility = View.VISIBLE
                           binding.textNoProducts.text = getString(R.string.no_items_available)
                       }
                       adapter.submitList(list as ArrayList<Products>)
                   }
               }
            }
        }

        viewModel.getProducts()
    }

    private fun String.findLength() : String{
        return ""+this.length
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,"onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG,"onRestoreInstanceState")
    }
}