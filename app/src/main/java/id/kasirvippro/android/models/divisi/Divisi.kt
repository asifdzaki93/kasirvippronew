package id.kasirvippro.android.models.divisi

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Divisi : Serializable {
    var id_divisi: String? = null
    var name_divisi: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }
}
