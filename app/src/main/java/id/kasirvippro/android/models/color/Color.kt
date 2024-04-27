package id.kasirvippro.android.models.color

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Richie on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Color : Serializable {
    val id_color: String? = null
    var name_color: String? = null
    var code_color: String? = null


    fun json(): String {
        return Gson().toJson(this)
    }
}
