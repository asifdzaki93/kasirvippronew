package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailJob : Serializable {
    var no_invoice: String? = null
    var note: String? = null //Important
    var date: String? = null  //Important
    var status: String? = null //Important
    var operator: String? = null //Important

    fun json(): String {
        return Gson().toJson(this)
    }
}
