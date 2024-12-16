package com.srinivas.apiwithtestcasevirtusa.views.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.srinivas.apiwithtestcasevirtusa.R
import com.srinivas.apiwithtestcasevirtusa.databinding.ActivityDetailsBinding
import com.srinivas.apiwithtestcasevirtusa.model.response.Products
import com.srinivas.apiwithtestcasevirtusa.utils.AppConstants.KEY_PRODUCT

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var products: Products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        products = intent.getSerializableExtra(KEY_PRODUCT) as Products
        binding.products = products
        Log.d("TAG", "{Prod::  $products }")
        Glide.with(this)
            .load(products.thumbnail)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.baseline_broken_image_24)
            .into(binding.imageThumbnail)
    }
}