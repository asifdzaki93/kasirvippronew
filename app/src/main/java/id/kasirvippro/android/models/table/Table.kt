package id.kasirvippro.android.models.table

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Table : Serializable {
    var id_table: String? = null
    var name_table: String? = ""
    var img: String? = ""
    var inc: String? = "0"


    fun json(): String {
        return Gson().toJson(this)
    }

    fun set(
        id_table: String, name_table: String, increment: String,
    ){
        this.id_table = id_table
        this.name_table = name_table
        this.inc = increment
    }
}
