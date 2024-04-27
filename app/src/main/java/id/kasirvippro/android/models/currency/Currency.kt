package id.kasirvippro.android.models.currency

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Currency : Serializable {
    var id_currency: String? = null
    var code_currency: String? = ""
    var inc: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(id_currency : String,code_currency : String,
            increment : String){
        this.id_currency = id_currency
        this.code_currency = code_currency
        this.inc = increment
    }
}
