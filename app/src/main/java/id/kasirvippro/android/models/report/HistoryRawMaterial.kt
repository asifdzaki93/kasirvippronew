package id.kasirvippro.android.models.report

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class HistoryRawMaterial : Serializable {
    var totalsales: String? = "0" //Important
    var totalnominal : String? = "0"
    var date : String? = "0"
    var detail: List<ReportRawMaterial>? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
