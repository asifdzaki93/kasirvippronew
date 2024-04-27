package id.kasirvippro.android.models.kelolatoko

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class DefaultKelolatoko : Serializable {
    var name_store:String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}