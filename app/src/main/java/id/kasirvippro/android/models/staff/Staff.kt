package id.kasirvippro.android.models.staff

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Staff : Serializable {
    var key: String? = null
    var email: String? = null
    var full_name: String? = ""
    var phone_number: String? = ""
    var address: String? = ""
    var city: String? = ""
    var img: String? = ""
    var level: String? = ""
    var id_store: String? = ""
    var name_store: String? = ""
    var salary: String? = ""
    var commission: String? = ""
    var allowance: String? = ""
    var piece: String? = ""
    var total_work: String? = "0"
    var work_procces: String? = "0"
    var work_finish: String? = "0"
    var work_paid: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }
}
