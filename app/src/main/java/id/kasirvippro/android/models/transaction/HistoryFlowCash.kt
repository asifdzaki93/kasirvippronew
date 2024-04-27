package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class HistoryFlowCash : Serializable {
    var totalorderall : String? = "0"
    var totalsales: String? = "0" //Important
    var totalpurchase: String? = "0" //Important
    var totalspend: String? = "0" //Important
    var totalreturn: String? = "0" //Important
    var money: String? = "0" //Important
    var totalnominal : String? = "0"
    var date : String? = "0"
    var detail: List<FlowCash>? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
