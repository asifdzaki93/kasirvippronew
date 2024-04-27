package id.kasirvippro.android.models.initial

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Initial : Serializable {
    var id:String? = null
    var modal_awal:String? = null
    var sift:String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}