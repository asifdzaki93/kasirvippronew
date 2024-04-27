package id.kasirvippro.android.models.customer

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Customer : Serializable {
    var id_customer: String? = null
    var name_customer: String? = ""
    var email: String? = ""
    var telephone: String? = ""
    var address: String? = ""
    var customercode: String? = ""
    var inc: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(
        id_customer: String, name_customer: String, telephone: String, email: String, address: String, customercode: String,
        increment: String,
    ){
        this.id_customer = id_customer
        this.name_customer = name_customer
        this.telephone = telephone
        this.email = email
        this.address = address
        this.customercode = customercode
        this.inc = increment
    }
}
