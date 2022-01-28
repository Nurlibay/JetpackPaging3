package uz.texnopos.paging3.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.paging3.data.model.Product
import uz.texnopos.paging3.databinding.ItemProductBinding

class ProductsAdapter : PagingDataAdapter<Product, ProductsAdapter.ProductViewHolder>(PassengersComparator) {

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun populateModel(product: Product) {
            binding.tvProductBrand.text = product.product_brand
            binding.tvProductCostPrice.text = product.product_cost_price.toString()
            binding.tvProductName.text = product.product_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.populateModel(it) }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.product_id == newItem.product_id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}