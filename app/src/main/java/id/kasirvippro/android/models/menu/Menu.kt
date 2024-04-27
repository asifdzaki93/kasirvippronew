package id.kasirvippro.android.models.menu

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable
import id.kasirvippro.android.R


/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Menu : Serializable {
    var id: Int? = 0
    var name: String? = ""
    var desc: String? = ""
    var image: Int? = R.drawable.logo_bulat

    fun json(): String {
        return Gson().toJson(this)
    }
}
