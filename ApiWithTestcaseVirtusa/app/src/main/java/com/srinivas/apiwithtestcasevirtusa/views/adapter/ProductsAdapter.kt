package com.srinivas.apiwithtestcasevirtusa.views.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.srinivas.apiwithtestcasevirtusa.R
import com.srinivas.apiwithtestcasevirtusa.databinding.ItemProductsBinding
import com.srinivas.apiwithtestcasevirtusa.model.response.Products
import com.srinivas.apiwithtestcasevirtusa.utils.AppConstants.KEY_PRODUCT
import com.srinivas.apiwithtestcasevirtusa.views.activity.DetailsActivity

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private val list = ArrayList<Products>()

    class ProductViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(products: Products) {
            Glide.with(binding.root.context)
                .load(products.thumbnail)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.baseline_broken_image_24)
                .into(binding.Thumbnail)

            binding.product = products
            binding.executePendingBindings()
            binding.cardItem.setOnClickListener{
                val intent = Intent(binding.root.context,DetailsActivity::class.java)
                intent.putExtra(KEY_PRODUCT,products)
                binding.root.context.startActivity(intent)
            }
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