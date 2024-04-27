package id.kasirvippro.android.models.category

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Category : Serializable {
    var id_category: String? = null
    var name_category: String? = ""
    var inc: String? = "0"

    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(id_category : String,name_category : String,
            increment : String){
        this.id_category = id_category
        this.name_category = name_category
        this.inc = increment
    }
}
