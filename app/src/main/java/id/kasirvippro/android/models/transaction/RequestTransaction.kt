package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class RequestTransaction : Serializable {
    var key: String ?= null
    var payment_type: Int? = 1
    var payment_amount: Int? = 0
    var total_order: Int? = 0
    var id_customer: String? = null
    var kurir: String? = null
    var name_customer: String? = null
    var name_store: String? = null
    var email_store: String? = null
    var nohp_store: String? = null
    var footer: String? = null
    var address_store: String? = null
    var id_supplier: String? = null
    var name_supplier: String? = null
    var point: String? = null
    var id_discount: String? = null
    var card: String? = null
    var note: String? = null
    var due_date: String? = null
    var product: List<Barang>? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var id_table: String? = null
    var ongkir: String? = null
    var divisi: String? = null
    var id: String? = null
    var complaint: String? = null
    var advice: String? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Barang : Serializable {
        var id_product: String? = null
        var amount_product: Int? = 0
        var new_price: String? = "0"
        var notes: String? = ""

        fun json(): String {
            return Gson().toJson(this)
        }
    }

    fun json(): String {
        return Gson().toJson(this)
    }
}
