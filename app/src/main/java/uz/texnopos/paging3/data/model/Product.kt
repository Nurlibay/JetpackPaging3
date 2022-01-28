package uz.texnopos.paging3.data.model

data class Product(
    val category_id: Int,
    val price_max: Int,
    val price_min: Int,
    val price_wholesale: Double,
    val product_brand: String,
    val product_cost_price: Int,
    val product_id: Int,
    val product_image: String,
    val product_name: String,
    val remained: Int
)