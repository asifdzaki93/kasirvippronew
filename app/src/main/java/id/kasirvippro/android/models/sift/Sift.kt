package id.kasirvippro.android.models.sift

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Richie on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Sift : Serializable {
    val id_sift: String? = null
    var name_sift: String? = null


    fun json(): String {
        return Gson().toJson(this)
    }
}
