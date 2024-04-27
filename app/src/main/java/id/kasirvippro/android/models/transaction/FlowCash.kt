package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class FlowCash : Serializable {
    var name_product: String? = null
    var name_supplier: String? = null
    var img: String? = null
    var no_invoice: String? = null //Important
    var date: String? = null  //Important
    var payment: String? = "0" //Important
    var totalorder: String? = "0" //Important
    var totalsales: String? = "0" //Important
    var totalpurchase: String? = "0" //Important
    var totalspend: String? = "0" //Important
    var totalreturn: String? = "0" //Important
    var money: String? = "0" //Important
    var nominal: String? = "0"
    var by: String? = null
    var status: String? = null //Important
    var type: String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
