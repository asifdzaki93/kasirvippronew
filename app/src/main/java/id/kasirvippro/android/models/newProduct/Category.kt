package id.kasirvippro.android.models.newProduct

data class Category (
    val status: String,
    val errCode: String,
    val msg: String,
    val data: List<CategoryData>
)

data class CategoryData (
    val id_category: String,
    val name_category: String
)
