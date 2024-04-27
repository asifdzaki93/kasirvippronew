package id.kasirvippro.android.models.closeShift

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class CloseShift : Serializable {
    var id:String? = null
    var cash_actual:String? = null
    var name_initial:String? = null
    var name_cashier:String? = null
    var initial_date:String? = null
    var cash_awal:String? = null
    var sales_debt:String? = null
    var sales_return:String? = null
    var variance:String? = null
    var ppn:String? = null
    var sc:String? = null
    var sales_non_cash:String? = null
    var shift:String? = null
    var flag:String? = null
    var sales_cash:String? = null
    var status:String? = null
    var total_sales:String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}