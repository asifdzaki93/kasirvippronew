package id.kasirvippro.android.models.supplier

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Supplier : Serializable {
    var id_supplier: String? = null
    var name_supplier: String? = ""
    var email: String? = ""
    var telephone: String? = ""
    var address: String? = ""
    var img: String? = ""
    var inc: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }


    fun set(id : String,name_supplier : String, telephone : String, email : String, address: String,
            increment : String){
        this.id_supplier = id
        this.name_supplier = name_supplier
        this.telephone = telephone
        this.email = email
        this.address = address
        this.inc = increment
    }
}

