package id.kasirvippro.android.models.newProduct

data class SubCategory (
    val status: String,
    val errCode: String,
    val msg: String,
    val data: List<SubCategoryData>
)

data class SubCategoryData (
    val id_subcategory: String,
    val id_category: String,
    val name_category: String
)
