package id.kasirvippro.android.models.report

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class ReportDaily : Serializable {
    val info: Info? = null
    val daily_report: List<Daily>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Info : Serializable {
        val name_store: String? = ""
        val date: String? = ""
        val total: String? = ""

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Daily : Serializable {
        val operator: String? = ""
        val sales_cash: String? = "0"
        val sales_debt: String? = "0"
        val sales_credit_card: String? = "0"
        val sales_debit_card: String? = "0"
        val bank: String? = "0"
        val pos: String? = "0"
        val dana: String? = "0"
        val sub_total: String? = "0"
        val name_store: String? = "0"
        val date: String? = "0"

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
