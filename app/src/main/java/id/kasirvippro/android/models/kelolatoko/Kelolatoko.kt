package id.kasirvippro.android.models.kelolatoko

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Kelolatoko : Serializable {
    var id_online_store:String? = null
    var name_store:String? = null
    var subdomain:String? = null
    var color_store:String? = null
    var address_store:String? = null
    var linkinstagram:String? = null
    var linkfb:String? = null
    var nowa:String? = null
    var operational_hour:String? = null
    var img: String? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}