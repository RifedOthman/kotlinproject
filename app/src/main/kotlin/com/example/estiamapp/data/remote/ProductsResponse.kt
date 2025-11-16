package com.example.estiamapp.data.remote

import com.example.estiamapp.data.model.ProductDto

data class ProductsResponse(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

