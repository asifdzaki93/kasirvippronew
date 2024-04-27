package id.kasirvippro.android.models.store

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Store : Serializable {
    val id_store: String? = null
    val type: String? = ""
    var name_store: String? = null
    var nohp: String? = ""
    var address: String? = ""
    var email: String? = ""
    var omset: String? = "0"
    var transaksi: String? = "0"
    var order: String? = "0"
    var tax: String? = "0"
    var service_charge: String? = "0"
    var number_store: String? = null
    var level: String? = null
    var footer: String? = ""
    var photo: String? = ""
    var currency: String? = ""
    var shift: String? = "0"
    var initial: String? = null
    var img: String? = null
    var menu_order: String? = null
    var menu_purchase: String? = null
    var menu_spending: String? = null
    var menu_transaction: String? = null
    var menu_debt: String? = null
    var menu_printlabel: String? = null
    var menu_manageorder: String? = null
    var menu_managestock: String? = null
    var menu_return: String? = null
    var menu_addon: String? = null
    var menu_othermenu: String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
