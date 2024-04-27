package id.kasirvippro.android.models.report

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class ReportProduct : Serializable {
    val info: Info? = null
    val sales_report: List<Sales>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Info : Serializable {
        val totallaba: String? = "0"
        val rata2: String? = "0"
        val amount: String? = "0"


        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Sales : Serializable {
        val id_product: String? = null
        var name_product: String? = null
        var first_date: String? = null
        var last_date: String? = null
        var totalorder: String? = "0"
        var sales: String? = "0"
        var last_stock: String? = "0"
        var purchase_price: String? = null
        var selling_price: String? = null
        var laba_rugi: String? = null
        var have_stock: String? = "0"
        var unit: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
