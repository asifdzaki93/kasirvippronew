package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailCOmmision : Serializable {
    var detail: String? = null //Important
    var date: String? = null  //Important
    var amount: String? = "0" //Important
    var status: String? = null //Important
    var type: String? = null
    fun json(): String {
        return Gson().toJson(this)
    }
}
