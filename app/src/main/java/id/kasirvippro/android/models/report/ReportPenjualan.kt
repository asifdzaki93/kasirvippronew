package id.kasirvippro.android.models.report

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class ReportPenjualan : Serializable {
    val info: Info? = null
    val sales_report: List<Penjualan>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Info : Serializable {
        val total_sales: String? = "0"
        val average: String? = "0"
        val number_transaction: String? = "0"
        val date: String? = ""
        val operator: String? = ""
        val name_store: String? = ""

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Penjualan : Serializable {
        val id_product: String? = null
        val name_product: String? = ""
        val amount: String? = "0"
        val totalprice: String? = "0"
        val selling_price: String? = "0"
        val unit: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
