package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Transaction : Serializable {
    var uuid: String? = null
    var name_product: String? = null
    var name_supplier: String? = null
    var img: String? = null
    var no_invoice: String? = null //Important
    var date: String? = null  //Important
    var payment: String? = "0" //Important
    var totalorder: String? = "0" //Important
    var nominal: String? = "0"
    var by: String? = null
    var status: String? = null //Important
    var type: String? = null
    var name_table: String? = null
    var name_customer: String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
    fun set(uuid: String?,name_product: String?, name_supplier: String?, img: String?, no_invoice: String?, date: String?, payment: String?, totalorder: String?, nominal: String?, by: String?, status: String?, type: String?){
        this.uuid = uuid
        this.name_product = name_product
        this.name_supplier = name_supplier
        this.img = img
        this.no_invoice = no_invoice
        this.date = date
        this.payment = payment
        this.totalorder = totalorder
        this.nominal = nominal
        this.by = by
        this.status = status
        this.type = type
    }
}
