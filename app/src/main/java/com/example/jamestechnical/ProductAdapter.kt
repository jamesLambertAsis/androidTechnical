package com.example.jamestechnical

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jamestechnical.databinding.ItemProductBinding
import com.example.jamestechnical.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val productList: List<Product>,
    private val callback: Callback
) : RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder>(){

    interface Callback{
        fun onProductClick(product: Product)
    }
    class ProductAdapterViewHolder(private val binding: ItemProductBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun binding(product: Product, callback: Callback){
            Picasso.get()
                .load(product.thumbnail)
                .into(binding.ivProduct)
            binding.tvDescription.text = product.title
            binding.tvRating.text = "Rating:${product.rating}"
            binding.tvStock.text = "Stock: ${product.stock}"
            binding.clProductContainer.setOnClickListener {
                callback.onProductClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        val product = productList[position]
        return holder.binding(product, callback)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}