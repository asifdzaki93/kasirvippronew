package id.kasirvippro.android.models.pulsaPpob

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailPpob : Serializable {
    var ref_id: String? = ""
    var customer_no: String? = ""
    var customer_name: String? = ""
    var price: String? = ""
    var buyer_sku_code: String? = ""
    var desc: String? = ""
    var phone: String? = ""
    var gbr: String? = ""
    var tagihan: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }

}
