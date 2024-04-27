package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DetailHistory : Serializable {
    var name_product: String? = null
    var name_store: String? = null
    var img: String? = null
    var detail: String? = null //Important
    var date: String? = null  //Important
    var stock: String? = "0" //Important
    var nominal: String? = "0"
    var by: String? = null
    var status: String? = null //Important
    var unit: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }
}
