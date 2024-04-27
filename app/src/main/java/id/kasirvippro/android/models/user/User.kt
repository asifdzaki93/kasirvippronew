package id.kasirvippro.android.models.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class User : Serializable {
    var key: String? = null
    var email: String? = null
    var full_name: String? = ""
    var phone_number: String? = ""
    var address: String? = ""
    var city: String? = ""
    var omset: String? = "0"
    var img: String? = ""
    var level: String? = ""
    var id_store: String? = ""
    var afiliasi: String? = ""
    var subdomain: String? = ""
    var type: String? = ""
    var signup: String? = ""
    var screen: String? = ""
    var saldo: String? = ""


    fun json(): String {
        return Gson().toJson(this)
    }
}
