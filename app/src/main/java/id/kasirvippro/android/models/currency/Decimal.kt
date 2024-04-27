package id.kasirvippro.android.models.currency

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Decimal : Serializable {
    var id_decimal: String? = null
    var code_decimal: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }
}
