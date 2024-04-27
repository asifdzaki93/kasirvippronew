package id.kasirvippro.android.models.ongkir

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Ongkir : Serializable {
    var id_ongkir: String? = null
    var name_ongkir: String? = ""
    var nominal: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }
}
