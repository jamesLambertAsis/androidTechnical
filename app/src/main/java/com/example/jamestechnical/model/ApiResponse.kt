package com.example.jamestechnical.model

data class ApiResponse (
    val skip: Int,
    val limit: Int,
    val total: Int,
    val products: List<Product>
)