package id.kasirvippro.android.models.role

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Role : Serializable {
    var id_role: String? = null
    var name_role: String? = ""
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
    var add_product: String? = null
    var add_category: String? = null
    var add_supplier: String? = null
    var add_customer: String? = null
    var add_discount: String? = null
    var report_product: String? = null
    var report_sumary: String? = null
    var report_daily: String? = null
    var report_accounting: String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
