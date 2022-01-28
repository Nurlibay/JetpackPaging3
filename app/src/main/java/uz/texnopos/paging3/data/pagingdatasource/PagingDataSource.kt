package uz.texnopos.paging3.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.texnopos.paging3.data.model.Product
import uz.texnopos.paging3.data.retrofit.ApiInterface
import java.lang.Exception

class PagingDataSource(
    private val api: ApiInterface
    ): PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getProducts(page, params.loadSize)
            LoadResult.Page(
                data = response.body()!!.payload.products,
                prevKey = if(page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if(response.body()!!.payload.products.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}