package id.kasirvippro.android.models.report

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class ReportLabaRugi : Serializable {
    val info: Info? = null
    val financial_statements: Keuangan? = null
    val sales_report: List<Penjualan>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Info : Serializable {
        val net_sales: String? = "0"
        val average: String? = "0"
        val amount_transaction: String? = "0"


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

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Keuangan : Serializable {
        val gross_sales: String? = "0"
        val discount: String? = "0"
        val cancellation: String? = "0"
        val net_sales: String? = "0"
        val tax: String? = "0"
        val admin: String? = "0"
        val total_income: String? = "0"
        val harga_pokok_penjualan: String? = "0"
        val gross_profit: String? = "0"
        val expenses: String? = "0"
        val salesreturn: String? = "0"

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
