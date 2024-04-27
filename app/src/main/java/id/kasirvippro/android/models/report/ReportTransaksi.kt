package id.kasirvippro.android.models.report

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class ReportTransaksi : Serializable {
    var id_product: String? = null
    var name_product: String? = null
    var first_date: String? = null
    var finish_date: String? = null
    var totalorder: String? = "0"
    var sales: String? = "0"
    var last_stock: String? = "0"
    var purchase_price: String? = null
    var selling_price: String? = null
    var laba_rugi: String? = null
    var have_stock: String? = "0"
    var unit: String? = null

    var totallaba: String? = null
    var rata2: String? = "0"
    var amount: String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
