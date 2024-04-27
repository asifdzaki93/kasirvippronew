package id.kasirvippro.android.models.recipe

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Recipe : Serializable {
    var id_recipe: String? = null
    var raw_material: String? = "0"
    var id_product: String? = "0"
    var name: String? = ""
    var name_product: String? = ""
    var detail: String? = ""
    var stock: String? = "0"
    var unit: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }
}
