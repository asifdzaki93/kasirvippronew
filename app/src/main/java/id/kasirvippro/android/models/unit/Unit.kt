package id.kasirvippro.android.models.unit

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Richie on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Unit : Serializable {
    val id_unit: String? = null
    var name_unit: String? = null


    fun json(): String {
        return Gson().toJson(this)
    }
}
