package com.example.jamestechnical

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.jamestechnical.databinding.DialogViewProductBinding
import com.example.jamestechnical.model.Product
import com.squareup.picasso.Picasso

class ViewProductDialog(private val product: Product, context: Context): Dialog(context) {

    lateinit var binding: DialogViewProductBinding
    lateinit var adapter: ViewProductDialogAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogViewProductBinding.inflate(layoutInflater)
        Picasso.get()
            .load(product.thumbnail)
            .into(binding.ivProduct)
        binding.tvTitle.text = "${product.brand}: ${product.title}"
        binding.tvDescription.text = product.description
        binding.tvPrice.text = "Php ${product.price}"
        binding.tvDiscount.text = "${product.discountPercentage}% Discount"
        binding.tvRating.text = "Rating: ${product.rating}"
        binding.tvStock.text = "Stock: ${product.stock}"
        adapter = ViewProductDialogAdapter(product.images)
        binding.rvMoreImg.adapter = adapter
        binding.ivClose.setOnClickListener {
            this.dismiss()
        }
        setContentView(binding.root)
    }

}