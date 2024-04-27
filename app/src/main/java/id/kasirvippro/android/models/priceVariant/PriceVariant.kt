package id.kasirvippro.android.models.priceVariant

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class PriceVariant : Serializable {
    var id_pricevariant: String? = null
    var id_product: String? = "0"
    var name: String? = ""
    var detail: String? = ""
    var minimal: String? = "0"
    var price: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }
}
