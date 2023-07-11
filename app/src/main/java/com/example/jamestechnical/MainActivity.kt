package com.example.jamestechnical

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jamestechnical.api.ProductsApiService
import com.example.jamestechnical.databinding.ActivityMainBinding
import com.example.jamestechnical.model.ApiResponse
import com.example.jamestechnical.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productsApiService: ProductsApiService
    private var productList = mutableListOf<Product>()
    private lateinit var adapter: ProductAdapter
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        getAllProducts()
        setContentView(binding.root)
    }

    private fun initAdapter(productList: List<Product>){
        binding.rvProducts.layoutManager = GridLayoutManager(
            applicationContext,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        binding.rvProducts.itemAnimator = DefaultItemAnimator()
        adapter = ProductAdapter(productList, object : ProductAdapter.Callback{
            override fun onProductClick(product: Product) {
                val dialog = ViewProductDialog(product, context)
                dialog.show()
            }
        })
        binding.rvProducts.adapter = adapter
    }

    private fun getAllProducts() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        productsApiService = retrofit.create(ProductsApiService::class.java)
        val call: Call<ApiResponse> = productsApiService.getCategories()
        call.enqueue(object : Callback<ApiResponse> {

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                productList.clear()
                productList.addAll(response.body()!!.products)
                initAdapter(productList)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("")
                    .setMessage(R.string.could_not_connect)
                    .setPositiveButton(R.string.ok) { dialog, id ->
                        dialog.dismiss()
                    }
                builder.create()
            }
        })
    }
}