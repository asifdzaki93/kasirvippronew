package id.kasirvippro.android.models.addOn

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class AddOn : Serializable {
    var id_addon: String? = null
    var id_product: String? = "0"
    var name_addon: String? = ""
    var nominal: String? = "0"
    var inc: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(id_addon : String,id_product : String,name_addon : String,nominal: String,
            increment : String){
        this.id_addon = id_addon
        this.id_product = id_product
        this.name_addon = name_addon
        this.nominal = nominal
        this.inc = increment
    }
}
