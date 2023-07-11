package com.example.jamestechnical.api

import com.example.jamestechnical.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductsApiService {
    @GET("products")
    fun getCategories(): Call<ApiResponse>
}