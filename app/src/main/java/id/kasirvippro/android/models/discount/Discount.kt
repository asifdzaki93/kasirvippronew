package id.kasirvippro.android.models.discount

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Discount : Serializable {
    var id_discount: String? = null
    var name_discount: String? = ""
    var note: String? = ""
    var type: String? = ""
    var nominal: String? = "0"
    var inc: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(id_discount : String,name_discount : String,note : String, type : String, nominal: String,
            increment : String){
        this.id_discount = id_discount
        this.name_discount = name_discount
        this.note = note
        this.type = type
        this.nominal = nominal
        this.inc = increment
    }
}
