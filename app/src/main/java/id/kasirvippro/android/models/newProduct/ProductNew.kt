package id.kasirvippro.android.models.newProduct

import id.kasirvippro.android.models.product.Product

data class ProductNew(
    val status: String,
    val errCode: String,
    val msg: String,
    val data: List<Product>?
)
