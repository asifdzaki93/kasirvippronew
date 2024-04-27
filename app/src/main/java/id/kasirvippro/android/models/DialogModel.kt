package id.kasirvippro.android.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class DialogModel : Serializable {
    var id: String? = ""
    var value: String? = ""

    fun json(): String {
        return Gson().toJson(this)
    }
}
