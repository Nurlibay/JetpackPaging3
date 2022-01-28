package uz.texnopos.paging3.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import uz.texnopos.paging3.data.pagingdatasource.PagingDataSource
import uz.texnopos.paging3.data.retrofit.ApiInterface

class ProductViewModel(private val api: ApiInterface) : ViewModel() {
    val products = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { PagingDataSource(api)
        }).flow.cachedIn(viewModelScope)
}