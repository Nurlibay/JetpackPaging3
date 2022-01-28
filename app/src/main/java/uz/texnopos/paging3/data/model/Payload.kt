package uz.texnopos.paging3.data.model

data class Payload(
    val current_page: Int,
    val last_page: Int,
    val products: List<Product>
)