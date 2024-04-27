package id.kasirvippro.android.models.customer

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class MedicalRecord : Serializable {
    var name_customer: String? = null
    var no_invoice: String? = null
    var complaint: String? = null
    var advice: String? = null //Important
    var date: String? = null  //Important
    var operator: String? = null //Important

    fun json(): String {
        return Gson().toJson(this)
    }
}
