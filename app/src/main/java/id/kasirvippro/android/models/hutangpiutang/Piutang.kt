package id.kasirvippro.android.models.hutangpiutang

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Piutang : Serializable {
    var datapiutang: Piutang? = null
    var list: List<Data>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Piutang : Serializable {
        var amount_debt: Double? = 0.0
        var nominal_debt: String? = null
        var due_date: Double? = 0.0
        var notyet_paidoff: Double? = 0.0

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Data : Serializable {
        var id_customer: String? = null
        var name_customer: String? = null
        var date: String? = null
        var no_invoice: String? = null
        var payment: String? = null
        var note: String? = null
        var totalorder: String? = null
        var totalpay: String? = null
        var changepay: String? = null
        var due_date: String? = null
        var status: String? = null

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
