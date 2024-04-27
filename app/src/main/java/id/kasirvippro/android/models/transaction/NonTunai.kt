package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class NonTunai : Serializable {
    var id_link: String? = null
    var name_link: String? = null
    var img: String? = null
    var inc: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(
        id_link: String, name_link: String, img: String,
        increment: String,
    ){
        this.id_link = id_link
        this.name_link = name_link
        this.img = img
        this.inc = increment
    }
}
