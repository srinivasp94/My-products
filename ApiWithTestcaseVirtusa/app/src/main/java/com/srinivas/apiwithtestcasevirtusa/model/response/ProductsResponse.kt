package com.srinivas.apiwithtestcasevirtusa.model.response

import java.io.Serializable

data class ProductsResponse(
    val products: List<Products>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class Products(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Float,
    val thumbnail: String,
) : Serializable