package id.kasirvippro.android.models.packages

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Packages : Serializable {
    var id_product: String? = null
    var sesi: String? = null
    var name_packages: String? = ""
    var details: String? = ""
    var price: String? = ""
    var img: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }
}
