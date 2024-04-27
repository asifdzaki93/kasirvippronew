package id.kasirvippro.android.models.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Network : Serializable {
    var key: String? = null
    var email: String? = null
    var full_name: String? = ""
    var phone_number: String? = ""
    var address: String? = ""
    var img: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }
}
