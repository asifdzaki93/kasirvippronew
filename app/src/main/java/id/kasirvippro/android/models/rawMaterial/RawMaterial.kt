package id.kasirvippro.android.models.rawMaterial

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class RawMaterial : Serializable {
    var id_raw_material: String? = null
    var name: String? = ""
    var unit: String? = ""
    var img: String? = ""
    var active: Boolean? = false
    var price: String? = "0"
    var stock: String? = "0"
    var description: String? = "-"
    var posisi: Boolean? = false

    fun json(): String {
        return Gson().toJson(this)
    }
}
