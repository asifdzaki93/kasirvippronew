package id.kasirvippro.android.models.pulsaPpob

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class PulsaPpob : Serializable {
    var product_name: String? = ""
    var category: String? = ""
    var brand: String? = ""
    var price: String? = ""
    var buyer_sku_code: String? = ""
    var desc: String? = ""
    var phone: String? = ""
    var gbr: String? = ""
    var buyer_product_status: String? = ""
    var seller_product_status: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }

}
