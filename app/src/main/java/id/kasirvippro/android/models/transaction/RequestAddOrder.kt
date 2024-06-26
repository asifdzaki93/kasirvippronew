package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class RequestAddOrder : Serializable {
    var key: String ?= null
    var total_order: Int? = 0
    var payment_type: Int? = 1
    var no_invoice: String? = null
    var product: List<Barang>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Barang : Serializable {
        var id_product: String? = null
        var amount_product: Int? = 0
        var notes: String? = ""

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
