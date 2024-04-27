package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailPayment : Serializable {
    var totalprice: Double? = 0.0
    var totalpay: Double? = 0.0
    var discount: Double? = 0.0
    var tax: Double? = 0.0
    var service_charge: Double? = 0.0
    var id_discount: String? = null
    var name_discount: String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
