package id.kasirvippro.android.models.typestore

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class TypeStore : Serializable {
    var id_typestore: String? = null
    var name: String? = ""
    var detail: String? = ""
    var inc: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(id_typestore : String,name : String,
            increment : String){
        this.id_typestore = id_typestore
        this.name = name
        this.inc = increment
    }
}
