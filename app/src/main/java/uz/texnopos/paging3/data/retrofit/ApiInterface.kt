package uz.texnopos.paging3.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.texnopos.paging3.data.model.Product
import uz.texnopos.paging3.data.model.response.GenericResponse

interface ApiInterface {

    @GET("api/products/")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<GenericResponse<List<Product>>>

}