package com.example.jamestechnical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jamestechnical.databinding.ItemProductImagesBinding
import com.squareup.picasso.Picasso

class ViewProductDialogAdapter(private val imgList: List<String>) :
    RecyclerView.Adapter<ViewProductDialogAdapter.ViewProductDialogAdapterViewHolder>() {


    class ViewProductDialogAdapterViewHolder(private val binding: ItemProductImagesBinding):RecyclerView.ViewHolder(binding.root){
        fun binding(imgUrl: String){
            Picasso.get()
                .load(imgUrl)
                .into(binding.ivMoreImg)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewProductDialogAdapterViewHolder {
        val view = ItemProductImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewProductDialogAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewProductDialogAdapterViewHolder, position: Int) {
        val img = imgList[position]
        return holder.binding(img)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

}