package uz.texnopos.paging3.ui.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.paging3.R
import uz.texnopos.paging3.databinding.FragmentProductBinding

class ProductFragment: Fragment(R.layout.fragment_product) {

    private lateinit var binding: FragmentProductBinding
    private val viewModel: ProductViewModel by viewModel()
    private lateinit var productsAdapter: ProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)
        setData()
    }

    private fun setData() {
        productsAdapter = ProductsAdapter()
        binding.rvProducts.adapter = productsAdapter
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productsAdapter.withLoadStateHeaderAndFooter(
                header = ProductLoadStateAdapter { productsAdapter.retry() },
                footer = ProductLoadStateAdapter { productsAdapter.retry() }
            )
            setHasFixedSize(true)
        }

        lifecycleScope.launch {
            viewModel.products.collectLatest { pagedData ->
                productsAdapter.submitData(pagedData)
            }
        }
    }
}