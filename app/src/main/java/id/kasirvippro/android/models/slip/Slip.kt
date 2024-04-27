package id.kasirvippro.android.models.slip

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Slip : Serializable {
    var key: String? = null
    var email: String? = null
    var full_name: String? = ""
    var phone_number: String? = ""
    var address: String? = ""
    var period: String? = ""
    var part: String? = ""
    var id_store: String? = ""
    var name_store: String? = ""
    var salary_fixed: String? = "0"
    var commission: String? = "0"
    var allowance: String? = "0"
    var piece: String? = "0"
    var total_sales: String? = "0"
    var total_salary: String? = "0"
    var presence: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }
}
