package id.kasirvippro.android.models.transaction

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.Gson
import java.io.Serializable

/**
 * Created by Ahmed on 7/15/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class Comission : Serializable {
    var totalorderall : String? = "0"
    var date : String? = "0"
    var totalcommision : String? = "0"
    var detail: List<DetailCOmmision>? = null

    fun json(): String {
        return Gson().toJson(this)
    }
}
