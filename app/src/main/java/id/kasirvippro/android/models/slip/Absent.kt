package id.kasirvippro.android.models.slip

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Absent : Serializable {
    var id_attendance: String? = null
    var staff: String? = null
    var date: String? = ""
    var hour: String? = ""
    var location: String? = ""
    var device: String? = ""
    var status: String? = ""


    fun json(): String {
        return Gson().toJson(this)
    }
}
