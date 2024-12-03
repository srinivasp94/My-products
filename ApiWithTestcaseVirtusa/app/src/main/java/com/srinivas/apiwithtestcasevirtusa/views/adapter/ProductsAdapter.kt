package com.srinivas.apiwithtestcasevirtusa.views.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srinivas.apiwithtestcasevirtusa.databinding.ItemProductsBinding
import com.srinivas.apiwithtestcasevirtusa.model.response.Products
import com.srinivas.apiwithtestcasevirtusa.views.activity.MainActivity

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private val list = ArrayList<Products>()

    class ProductViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(products: Products) {
            binding.product = products
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding = binding)
    }

    override fun getItemCount(): Int {
        return list.size ?: 0
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list : ArrayList<Products>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }



}